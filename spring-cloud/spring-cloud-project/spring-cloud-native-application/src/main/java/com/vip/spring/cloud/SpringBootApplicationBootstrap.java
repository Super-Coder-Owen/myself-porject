package com.vip.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@EnableAutoConfiguration
@RestController
public class SpringBootApplicationBootstrap {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册一个bean
        context.registerBean("helloWorld", String.class, "Hello World!!!");
        context.setId("owen");
        // 启动上下文
        context.refresh();
        new SpringApplicationBuilder(SpringBootApplicationBootstrap.class)
                .parent(context)
                .run(args);
        //SpringApplication.run(SpringBootApplicationBootstrap.class, args);
    }

    @Autowired
    @Qualifier("helloWorld") // Bean名称
    private String message;

    @GetMapping("")
    public String index() {
        return message;
    }

}
