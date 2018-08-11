package com.vip.chapter1.thread.impl;

/**
 *
 */
public class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println("runnable test...");
    }

    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        Thread thread = new Thread(runnableDemo);
        thread.start();
    }
}
