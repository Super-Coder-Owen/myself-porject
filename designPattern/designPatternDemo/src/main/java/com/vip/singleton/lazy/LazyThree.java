package com.vip.singleton.lazy;

/**
 * 懒汉式 ->默认加载的时候不实例化，在需要用到的时候才实例化（spring延时加载）
 * 静态内部类
 * 只有在外部类被调用的时候内部类才会加载
 */
public class LazyThree {

    private static boolean initialized = false;

    private LazyThree() {
        /**
         * 防止被反射
         */
        synchronized (LazyThree.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例被侵犯");
            }
        }
    }

    //static 是为了单例空间的共享
    //final 方法不能被重写，重载
    public static final LazyThree getInstance() {
        return LazyHolder.lazyThree;
    }

    private static class LazyHolder {
        private static final LazyThree lazyThree = new LazyThree();
    }

}
