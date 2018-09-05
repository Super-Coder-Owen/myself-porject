package com.vip.reactive;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class SpringEventDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 默认是同步非阻塞
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();

        // 创建线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 切换成异步非阻塞
        multicaster.setTaskExecutor(executorService);

        // 增加事件监听
        multicaster.addApplicationListener(event -> {
            System.out.printf("[线程%s]event:%s\n", Thread.currentThread().getName(), event); // 当前执行线程名称
        });

        // 广播事件
        multicaster.multicastEvent(new PayloadApplicationEvent("Hello World!", "Hello World!"));

        // 关闭线程池
        executorService.shutdown();
    }
}
