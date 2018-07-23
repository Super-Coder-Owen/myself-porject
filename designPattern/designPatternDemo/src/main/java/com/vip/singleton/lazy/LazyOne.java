package com.vip.singleton.lazy;

/**
 * 懒汉式 ->默认加载的时候不实例化，在需要用到的时候才实例化（spring延时加载）
 * 线程不安全
 */
public class LazyOne {
    public LazyOne() {
    }

    public static LazyOne lazyOne = null;

    public static LazyOne getInstance() {
        if (null == lazyOne) {
            lazyOne = new LazyOne();
        }
        return lazyOne;
    }
}
