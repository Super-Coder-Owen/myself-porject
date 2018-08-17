package com.spring.orm.extend.springJdbc;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContextProvider instance() {
        return (ApplicationContextProvider) applicationContext.getBean("applicationContextProvider");
    }

    public ApplicationContextProvider() {
        super();
    }

    public synchronized ApplicationContext applicationContext() {
        return ApplicationContextProvider.applicationContext;
    }

    @Override
    public synchronized void setApplicationContext(ApplicationContext anApplicationContext) throws BeansException {
        if (ApplicationContextProvider.applicationContext == null) {
            ApplicationContextProvider.applicationContext = anApplicationContext;
        }
    }
}