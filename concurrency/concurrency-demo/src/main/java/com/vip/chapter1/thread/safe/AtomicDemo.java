package com.vip.chapter1.thread.safe;

/**
 * 原子性
 */
public class AtomicDemo {
    private static int count = 1;

    public static void inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::inc).start();
        }
        Thread.sleep(4000);
        System.out.println("运行结果:" + count);
    }
}
