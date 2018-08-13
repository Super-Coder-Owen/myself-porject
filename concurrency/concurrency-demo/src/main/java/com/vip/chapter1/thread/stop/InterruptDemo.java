package com.vip.chapter1.thread.stop;

import java.util.concurrent.TimeUnit;

/**
 * 线程终止
 */
public class InterruptDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }, "interrupt");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); // 设置interrupt标识为true
    }
}
