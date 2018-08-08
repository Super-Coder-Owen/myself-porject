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
        // 原生方法
        Method m = this.targetObj.getClass().getMethod(method.getName(), method.getParameterTypes());

        // 通过原生方法去找
        if (this.aopConfig.contains(m)) {
            AopConfig.Aspect aspect = this.aopConfig.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }
        Object object = method.invoke(this.targetObj, args);
        if (this.aopConfig.contains(m)) {
            AopConfig.Aspect aspect = this.aopConfig.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }
        return object;
    }

    public void setConfig(AopConfig config) {
        this.aopConfig = config;
    }
}
