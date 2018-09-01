package com.vip.chapter4.thread.safe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 */
public class ConditionNotify extends Thread {
    private Lock lock;
    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("signal start !!!");
            condition.signal(); // 唤醒
            System.out.println("signal end !!!");
        } finally {
            lock.unlock();
        }
    }
}
