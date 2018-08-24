package com.vip.mvc;

import com.vip.mvc.service.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 *
 */
@ComponentScan(basePackages = "com.vip.mvc.service")
@EnableTransactionManagement
public class SpringApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringApplication.class); // 注册SpringApplication,扫描com.vip.mvc.service
        context.refresh(); // 启动

        context.getBeansOfType(EchoService.class).forEach((beanName, bean) -> {
            System.err.println("Bean name:" + beanName + ",bean:" + bean);
            bean.echo("Hello World!");
        });
        context.close(); // 关闭
    }

    @Component("myTxName")
    public static class MyPlatformTransactionManager implements PlatformTransactionManager {
        @Override
        public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
            return new DefaultTransactionStatus(
                    null, true, true,
                    definition.isReadOnly(), true, null);
        }

        @Override
        public void commit(TransactionStatus status) throws TransactionException {
            System.err.println("commit!!!");
        }

        @Override
        public void rollback(TransactionStatus status) throws TransactionException {
            System.err.println("rollback!!!");
        }
    }
}
