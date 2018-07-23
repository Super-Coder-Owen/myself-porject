package com.vip.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;

/**
 *
 *
 */
public class SpringEventListenerDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        //添加事件监听器
        /*context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.err.println("监听事件：" + event);
            }
        });
*/
        context.addApplicationListener(new ClosedListener());
        context.addApplicationListener(new RefreshedListener());
        // 启动spring应用上下文
        context.refresh();

        // ContextRefreshedEvent
        // PayloadApplicationEvent
        // MyEvent
        // ContextClosedEvent
        // 发布事件
        context.publishEvent("Hello Word!");

        context.publishEvent(new MyEvent("Hello Word 2018"));
        // 关闭应用上下文
        context.close();
    }

    private static class MyEvent extends ApplicationEvent {
        public MyEvent(Object source) {
            super(source);
        }
    }

    private static class ClosedListener implements ApplicationListener<ContextClosedEvent> {
        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            System.out.println("关闭上下文：" + event);
        }
    }

    private static class RefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("启动上下文：" + event);
        }
    }
}
