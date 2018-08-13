package com.vip.chapter1.thread.stop;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class InterruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
               boolean in = Thread.currentThread().isInterrupted();
                if (in){
                    System.out.println("before:"+in);
                    Thread.interrupted();// 设置复位
                    System.out.println("after:"+Thread.currentThread().isInterrupted());
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //终断
    }
}
