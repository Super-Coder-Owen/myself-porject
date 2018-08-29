package com.vip.chapter4.thread.safe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 */
public class LockDemo {
    private static Lock lock = new ReentrantLock();// 公平重入锁和非公平重入锁
    private static int count = 0;

    public static void addCount() throws InterruptedException {
        lock.lock(); // 获得锁
        count++;
        lock.unlock(); // 释放锁
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    LockDemo.addCount();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }
        Thread.sleep(4000);
        System.out.println(count);
    }
}
