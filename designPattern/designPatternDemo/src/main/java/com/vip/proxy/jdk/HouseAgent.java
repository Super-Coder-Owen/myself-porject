package com.vip.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 */
public class HouseAgent implements InvocationHandler {
    private PersonInterface personTarget;

    public Object getInstance(PersonInterface personTarget) {
        this.personTarget = personTarget;
        return Proxy.newProxyInstance(personTarget.getClass().getClassLoader(), personTarget.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("house agent start find...");
        return method.invoke(this.personTarget, args);
    }
}
