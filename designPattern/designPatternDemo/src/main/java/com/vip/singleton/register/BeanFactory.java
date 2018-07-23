package com.vip.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册登记式:每使用一次，都往固定的容器中去注册并将使用过的对象进行缓存，
 * 下次去取得时候，直接从缓存中取，以保证每次取得的都是同一个对象
 * IOC中的单例模式，就是典型的注册登记式单例
 */
public class BeanFactory {
    private BeanFactory() {

    }

    private static Map<String, Object> ico = new ConcurrentHashMap<String, Object>();

    public static Object getBean(String className) {
        if (ico.containsKey(className)) {
            return ico.get(className);
        } else {
            try {
                Object obj = Class.forName(className).newInstance();
                ico.put(className, obj);
                return obj;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
