package com.vip.chapter4.thread.safe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 */
public class ConditionWait extends Thread {
    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("await start !!!");
            condition.await(); // 阻塞
            System.out.println("await end !!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
