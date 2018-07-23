package com.vip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringBootProjectApplication {

    public static void main(String[] args) {
        /*// 第一种写法
        SpringApplication.run(SpringBootProjectApplication.class, args);// 默认8080端口

        // 第二种写法
        new SpringApplicationBuilder(SpringBootProjectApplication.class)
                .properties("server.port=0") //随机向操作系统要可用端口
                .run(args);

        // 第三种写法
        SpringApplication springApplication = new SpringApplication(SpringBootProjectApplication.class);
        Map<String,Object> properties = new HashMap<>();
        properties.put("server.port",0); // 随机向系统要可用端口
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);*/


        SpringApplication springApplication = new SpringApplication(SpringBootProjectApplication.class);
        Map<String,Object> properties = new HashMap<>();
        properties.put("server.port",0); // 随机向系统要可用端口
        springApplication.setDefaultProperties(properties);
        // 设置为非Web应用
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context =springApplication.run(args);
        System.out.println(context.getBean(SpringBootProjectApplication.class));
        // 输出当前spring boot 应用的ApplicationContext的类名
        System.out.println("当前spring应用上下文的类："+context.getClass().getName());

    }
}
