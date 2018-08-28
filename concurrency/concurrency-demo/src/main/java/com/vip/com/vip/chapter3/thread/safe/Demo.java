package com.vip.com.vip.chapter3.thread.safe;

/**
 *
 */
public class Demo {
    public static void main(String[] args) {
        Object lock = new Object();
        ThreadWait threadWait = new ThreadWait(lock);
        threadWait.start();
        ThreadNotify threadNotify = new ThreadNotify(lock);
        threadNotify.start();
    }
}
