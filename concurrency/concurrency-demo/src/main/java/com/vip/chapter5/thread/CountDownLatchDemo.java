package com.vip.chapter5.thread;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            countDownLatch.countDown(); // 递减
        }).start();
        new Thread(() -> {
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            countDownLatch.countDown();
        }).start();
        countDownLatch.await(); // 阻塞
        System.out.println("end...");
    }
}
