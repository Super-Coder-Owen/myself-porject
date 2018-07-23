package com.vip.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class SpringAnnotationDemo {
    public static void main(String[] args) {
        // XML配置文件驱动ClassPathXmlApplicationContext
        // Annotation驱动
        // 找BeanDefinition
        // @Bean @Configuration
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册一个Configuration class = SpringAnnotationDemo
        context.register(SpringAnnotationDemo.class);
        // 上下文启动
        context.refresh();
        System.out.println(context.getBean(SpringAnnotationDemo.class));
    }
}
