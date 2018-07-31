package com.vip.spring.servlet;

import com.vip.demo.DemoAction;
import com.vip.spring.annotation.Autowired;
import com.vip.spring.annotation.Controller;
import com.vip.spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class DispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();
    // bean容器
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    private List<String> classNames = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 开始初始化

        // 定位
        doLoadConfig(config.getInitParameter("contextConfigLocation").replace("classpath:", ""));
        // 加載
        doScanner(contextConfig.getProperty("scan.package"));
        // 注冊
        doRegistry();
        // 依賴注入
        doAutowired();

        DemoAction demoAction = (DemoAction) beanMap.get("demoAction");
        demoAction.test(null,null,"owen");

        //如果是Spring MVC会多设计一个HandlerMapping
        //将@RequestMapping中配置的url和一个Method关联上
        //以便于从浏览器获得用户输入的url以后，能够找到具体执行的Method通过反射区调用
        initHandlerMapping();
    }

    private void initHandlerMapping() {
    }

    private void doAutowired() {
        if (beanMap.isEmpty()) {
            return;
        }
        try {
            for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
                Field[] fields = entry.getValue().getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!field.isAnnotationPresent(Autowired.class)) {
                        continue;
                    }

                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String beanName = autowired.value().trim();
                    if ("".equals(beanName)) {
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);
                    field.set(entry.getValue(), beanMap.get(beanName));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doRegistry() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                Class clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    beanMap.put(beanName, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = (Service) clazz.getAnnotation(Service.class);
                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }
                    beanMap.put(beanName, clazz.newInstance());

                    Class<?>[] interfaces = clazz.getInterfaces();
                    if (null != interfaces && interfaces.length > 0) {
                        for (Class<?> inter : interfaces) {
                            beanMap.put(inter.getName(), clazz.newInstance());
                        }
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "\\/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", ""));
            }
        }
    }

    private void doLoadConfig(String local) {
        // 在spring中是通过Reader去查找和定位
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(local);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
