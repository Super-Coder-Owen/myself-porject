package com.vip.chapter4.thread.safe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionWait conditionWait = new ConditionWait(lock, condition);
        conditionWait.start();
        ConditionNotify conditionNotify = new ConditionNotify(lock, condition);
        conditionNotify.start();
    }
}
