package com.vip.spring.formework.context;

import com.vip.spring.formework.annotation.Autowired;
import com.vip.spring.formework.annotation.Controller;
import com.vip.spring.formework.annotation.Service;
import com.vip.spring.formework.aop.AopConfig;
import com.vip.spring.formework.beans.BeanDefinition;
import com.vip.spring.formework.beans.BeanPostProcessor;
import com.vip.spring.formework.beans.BeanWrapper;
import com.vip.spring.formework.context.support.XmlBeanDefinitionReader;
import com.vip.spring.formework.core.BeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class ClassPathXmlApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    private String[] configLocations;

    private XmlBeanDefinitionReader reader;


    // 保证注册式单例容器
    private Map<String, Object> beanCacheMap = new HashMap<>();
    // 存储所有被代理过的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public ClassPathXmlApplicationContext(String... locations) {
        this.configLocations = locations;
        this.refresh();
    }

    public void refresh() {
        // 定位
        this.reader = new XmlBeanDefinitionReader(this.configLocations);
        // 加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();
        // 注册
        doRegistry(beanDefinitions);
        // 依赖注入（lazy-init = false）,要执行依赖注入，在这里自动调用getBean方法
        doAutowired();


        // 测试
      /*  DemoAction demoAction = (DemoAction) this.getBean("demoAction");
        demoAction.test(null,null,"owen");*/
    }


    private void doAutowired() {
        for (Map.Entry<String, BeanDefinition> entry : this.beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            if (!entry.getValue().getLazyInit()) {
               Object obj = getBean(beanName);
                System.err.println(obj.getClass());
            }
        }

        for (Map.Entry<String, BeanWrapper> entry : this.beanWrapperMap.entrySet()) {
            populateBean(entry.getKey(), entry.getValue().getOriginalInstance());
        }
    }

    public void populateBean(String beanName, Object instance) {
        Class<?> clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class))) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                Autowired autowired = field.getAnnotation(Autowired.class);

                String autowiredBeanName = autowired.value().trim();
                if ("".equals(autowiredBeanName)) {
                    autowiredBeanName = field.getType().getName();
                }
                field.setAccessible(true);

                field.set(instance, this.beanWrapperMap.get(autowiredBeanName).getWrappedInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 读取BeanDefinition中的信息
    // 通过反射创建一个实例并返回
    // 不会返回原始bean,用wrapper包装
    // 装饰器模式：保留原来的OOP关系
    // 对它进行扩展、增强
    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        String className = beanDefinition.getBeanClassName();
        try {
            // 生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();

            Object instance = instanceBean(beanDefinition);
            if (null == instance) {
                return null;
            }
            // 在实例初始化之前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

            BeanWrapper beanWrapper = new BeanWrapper(instance);
            beanWrapper.setAopConfig(instanceAopConfig(beanDefinition));
            beanWrapper.setBeanPostProcessor(beanPostProcessor);
            this.beanWrapperMap.put(beanName, beanWrapper);

            // 在实例初始化之后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance, beanName);

            // populateBean(beanName, instance);

            return this.beanWrapperMap.get(beanName).getWrappedInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object instanceBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = Class.forName(className);
            if (beanCacheMap.containsKey(className)) {
                instance = beanCacheMap.get(className);
            } else {
                instance = clazz.newInstance();
                this.beanCacheMap.put(className, instance);
            }
            return instance;
        } catch (Exception e) {

        }
        return null;
    }


    // 将BeanDefinition注册到beanDefinitionMap
    private void doRegistry(List<String> beanDefinitions) {
        try {
            // beanName有三种情况 1.默认是首字母小写 2.自定义名称 3.接口注入
            for (String className : beanDefinitions) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) { // 是接口，不能实例化，用它的实现类来实例化
                    continue;
                }
                BeanDefinition beanDefinition = reader.registerBean(className);
                if (null != beanDefinition) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }

                Class[] interFaces = beanClass.getInterfaces();
                if (null != interFaces && interFaces.length > 0) {
                    for (Class<?> clazz : interFaces) {
                        this.beanDefinitionMap.put(clazz.getName(), beanDefinition);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }


    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfigContext() {
        return reader.getContextConfig();
    }

    private AopConfig instanceAopConfig(BeanDefinition beanDefinition) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        AopConfig config = new AopConfig();
        String expression = this.reader.getContextConfig().getProperty("point.cut");
        String[] before = this.reader.getContextConfig().getProperty("aspectBefore").split("\\s");
        String[] after = this.reader.getContextConfig().getProperty("aspectAfter").split("\\s");
        String className = beanDefinition.getBeanClassName();
        Class<?> clazz = Class.forName(className);
        Pattern pattern = Pattern.compile(expression);

        Class<?> aspectClazz = Class.forName(before[0]);
        for (Method m : clazz.getMethods()) {
            Matcher matcher = pattern.matcher(m.toString());
            if (matcher.matches()) {
                config.put(m, aspectClazz.newInstance(), new Method[]{aspectClazz.getMethod(before[1]), aspectClazz.getMethod(after[1])});
            }
        }

        return config;
    }
}
