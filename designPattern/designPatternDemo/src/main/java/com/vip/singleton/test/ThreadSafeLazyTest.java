package com.vip.singleton.test;

import com.vip.singleton.lazy.LazyThree;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 饿汉式和懒汉式性能测试
 */
public class ThreadSafeLazyTest {
/*    public static void main(String[] args) {
        int count = 2000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            //LazyOne.getInstance();
            LazyTwo.getInstance();
            //LazyThree.getInstance();

        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时->" + (end-start));
    }*/

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class<?> clazz = LazyThree.class;
        Constructor constructor = clazz.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        // 暴力初始化
        Object obj = constructor.newInstance();
        Object obj1 = constructor.newInstance();
        System.out.println(obj);
    }
}

