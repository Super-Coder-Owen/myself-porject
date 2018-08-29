package com.vip.chapter3.thread.safe;

/**
 *
 */
public class SynchronizedDemo {
    // 同步方法
    public synchronized void doSth() { // 全局锁：对于多个实例来说，唯一占用，都是同一把锁
        System.out.println("Hello World!");
    }

    public void doSth1() {
        // 同步代码块 SynchronizedDemo.class
        synchronized (SynchronizedDemo.class) { // 全局锁
            System.out.println("Hello World!");
        }

        // 同步代码块 this
        synchronized (this) { // 当前对象锁：对于多个实例来说，互不影响，多个锁
            System.out.println("Hello World!");
        }
    }

    public static void main(String[] args) {

    }
}
