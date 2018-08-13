package com.vip.chapter1.thread.safe;

import java.util.concurrent.TimeUnit;

/**
 * 可见性
 */
public class VisibilityDemo {
    private volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;
    }
}
