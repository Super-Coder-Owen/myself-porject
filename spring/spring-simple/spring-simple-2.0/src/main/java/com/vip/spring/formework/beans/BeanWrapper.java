package com.vip.spring.formework.beans;

import com.vip.spring.formework.aop.AopConfig;
import com.vip.spring.formework.aop.AopProxy;
import com.vip.spring.formework.core.FactoryBean;

/**
 *
 */
public class BeanWrapper extends FactoryBean {
    private AopProxy aopProxy = new AopProxy();

    // 观察者模式
    // 支持事件响应，会有一个监听
    private BeanPostProcessor beanPostProcessor;

    private Object wrapperInstance;

    // 原始的通过反射new出来，要包装起来，存下来
    private Object originalInstance;

    public BeanWrapper(Object object) {

        // 动态代码添加进来
        this.wrapperInstance = aopProxy.getProxy(object);
        this.originalInstance = object;
    }

    public Object getWrappedInstance() {
        return this.wrapperInstance;
    }

    /**
     * 返回代理以后的class
     *
     * @return
     */
    public Class<?> getWrappedClass() {
        return this.wrapperInstance.getClass();
    }

    public BeanPostProcessor getBeanPostProcessor() {
        return beanPostProcessor;
    }

    public void setBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessor = beanPostProcessor;
    }

    public void setAopConfig(AopConfig config) {
        this.aopProxy.setConfig(config);
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }
}
