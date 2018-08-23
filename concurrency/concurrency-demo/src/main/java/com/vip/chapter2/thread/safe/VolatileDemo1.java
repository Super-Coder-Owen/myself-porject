package com.vip.chapter2.thread.safe;


public class VolatileDemo1 {
    private static volatile VolatileDemo1 instance = null;

    public static VolatileDemo1 getInstance() {
        if (instance == null) {
            instance = new VolatileDemo1();
        }
        return instance;
    }

    public static void main(String[] args) {
        VolatileDemo1.getInstance();
    }
}
