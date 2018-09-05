package com.vip.reactive;

/**
 *
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        println("hello world1!");

        Thread thread = new Thread(() -> {
            println("hello world 2018!"); // 线程任务
        }, "sub-thread"); // 线程名称
        thread.start(); // 启动线程
        thread.join(); // 等待线程销毁
        println("hello world12");
    }

    private static void println(String message) {
        System.out.printf("[线程:%s]%s\n", Thread.currentThread().getName(), message);
    }
}
