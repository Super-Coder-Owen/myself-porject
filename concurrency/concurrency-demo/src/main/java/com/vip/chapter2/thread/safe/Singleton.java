package com.vip.chapter2.thread.safe;

/**
 *
 */
public class Singleton {
    private volatile static Singleton singleton;

    public static Singleton getSingleton() {
        if (null == singleton) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Singleton.getSingleton());
        }
    }
}
