package com.vip.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动类
 */
@SpringBootApplication
public class FirstApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FirstApp.class);
        Map<String,Object> properties = new HashMap<>();
        properties.put("server.port",0);
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }
}
