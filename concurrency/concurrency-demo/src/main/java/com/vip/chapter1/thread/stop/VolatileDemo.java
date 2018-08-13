package com.vip.chapter1.thread.stop;

/**
 *
 */
public class VolatileDemo {
    private volatile static boolean stop = false;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
            System.out.println(i);
        });
        thread.start();
        try {
            Thread.sleep(1000);
            stop = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
