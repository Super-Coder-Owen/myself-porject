package com.vip.chapter1.thread.application;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 */
public class SaveProcessor extends Thread implements RequestProcessor {
    // 一个阻塞队列
    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue();

    @Override
    public void run() {
        while (true) {
            try {
                Request request = this.linkedBlockingQueue.take();
                System.out.println("save data:" + request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void processorRequest(Request request) {
        this.linkedBlockingQueue.add(request);
    }
}
