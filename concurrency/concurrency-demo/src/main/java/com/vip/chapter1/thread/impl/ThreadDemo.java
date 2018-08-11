package com.vip.chapter1.thread.impl;

/**
 *
 */
public class ThreadDemo extends Thread {

    @Override
    public void run() {
        System.out.println("thread test...");
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }
}
