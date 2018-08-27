package com.vip.chapter2.thread.safe;


public class VolatileDemo {
    private static volatile VolatileDemo instance = null;

    public static VolatileDemo getInstance() {
        if (instance == null) {
            instance = new VolatileDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        VolatileDemo.getInstance();
    }
}
