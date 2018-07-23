package com.vip.singleton.lazy;

/**
 * 懒汉式 ->默认加载的时候不实例化，在需要用到的时候才实例化（spring延时加载）
 * 改进 synchronized 线程安全，性能有影响
 */
public class LazyTwo {
    public LazyTwo() {
    }

    public static LazyTwo lazyTwo = null;

    public static synchronized LazyTwo getInstance() {
        if (null == lazyTwo) {
            lazyTwo = new LazyTwo();
        }
        return lazyTwo;
    }
}
