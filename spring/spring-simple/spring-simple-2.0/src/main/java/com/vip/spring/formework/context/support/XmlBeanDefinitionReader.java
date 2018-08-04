package com.vip.spring.formework.context.support;

import com.vip.spring.formework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 配置文件进行查找、读取、解析
 */
public class XmlBeanDefinitionReader {

    private Properties contextConfig = new Properties();

    private List<String> registerBeanClasses = new ArrayList<>();

    public XmlBeanDefinitionReader(String... locations) {
        // 在spring中是通过Reader去查找和定位
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0]);
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

        doScanner(contextConfig.getProperty("scan.package"));
    }

    public List<String> loadBeanDefinitions() {
        return this.registerBeanClasses;
    }

    /**
     * 每注册一个className,就返回一个BeanDefinition
     * 对配置信息包装
     *
     * @param className
     * @return
     */
    public BeanDefinition registerBean(String className) {
        if (this.registerBeanClasses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".") + 1)));
            return beanDefinition;
        }
        return null;
    }

    public Properties getContextConfig() {
        return contextConfig;
    }

    /**
     * 递归扫描
     *
     * @param packageName
     */
    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "\\/"));
        if (null != url) {
            File classDir = new File(url.getFile());
            for (File file : classDir.listFiles()) {
                if (file.isDirectory()) {
                    doScanner(packageName + "." + file.getName());
                } else {
                    registerBeanClasses.add(packageName + "." + file.getName().replace(".class", ""));
                }
            }
        }
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
