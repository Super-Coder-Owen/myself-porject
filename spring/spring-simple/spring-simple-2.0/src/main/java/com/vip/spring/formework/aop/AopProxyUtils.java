package com.vip.spring.formework.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 *
 */
public class AopProxyUtils {
    public static Object getTargetObject(Object proxy) throws Exception {
        // 先判断对象是不是被代理的对象
        if (!isAopProxy(proxy)) {
            return proxy;
        }
        return getProxyTargetObject(proxy);
    }

    private static boolean isAopProxy(Object object) {
        return Proxy.isProxyClass(object.getClass());
    }

    private static Object getProxyTargetObject(Object proxy) throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy = (AopProxy) h.get(proxy);
        Field target = aopProxy.getClass().getDeclaredField("targetObj");
        target.setAccessible(true);
        return target.get(aopProxy);
    }
}
