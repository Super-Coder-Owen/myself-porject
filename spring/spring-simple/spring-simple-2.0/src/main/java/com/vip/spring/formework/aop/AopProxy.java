package com.vip.spring.formework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理对象
 */
public class AopProxy implements InvocationHandler {
    private AopConfig aopConfig;
    private Object targetObj;

    public Object getProxy(Object instance) {
        this.targetObj = instance;
        Class<?> clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (this.aopConfig.contains(method)) {
            AopConfig.Aspect aspect = this.aopConfig.get(method);
            aspect.getPoints()[0].invoke(aspect);
        }
        Object object = method.invoke(this.targetObj, args);
        if (this.aopConfig.contains(method)) {
            AopConfig.Aspect aspect = this.aopConfig.get(method);
            aspect.getPoints()[1].invoke(aspect);
        }
        return object;
    }

    public void setConfig(AopConfig config) {
        this.aopConfig = config;
    }
}
