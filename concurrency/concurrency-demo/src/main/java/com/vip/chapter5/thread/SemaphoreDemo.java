package com.vip.chapter5.thread;

import java.util.concurrent.Semaphore;

/**
 * 信号量，控制并发线程数
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new DoAnyThing(i, semaphore).start();
        }
    }

    static class DoAnyThing extends Thread {
        private int num;
        private Semaphore semaphore;

        public DoAnyThing(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire(); // 获取令牌
                System.out.println("第" + num + "线程进入");
                Thread.sleep(2000);
                semaphore.release(); // 释放令牌
                System.out.println("第" + num + "线程释放");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
