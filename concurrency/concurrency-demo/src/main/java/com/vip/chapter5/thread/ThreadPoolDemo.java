package com.vip.chapter5.thread;

import java.util.concurrent.ExecutorService;

/**
 * 线程池
 */
public class ThreadPoolDemo implements Runnable {
   // static ExecutorService executorService = Executors.newFixedThreadPool(3);
   // 自定义监控
   static ExecutorService executorService = MyExecutors.newMyMyExecutors();
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ThreadPoolDemo());
        }
        executorService.shutdown();
    }
}
