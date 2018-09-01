package com.vip.chapter4.thread.safe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class RWLockDemo {
    static Map<String, String> map = new HashMap<>();
    static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    static Lock rLock = rwLock.readLock(); // 读锁
    static Lock wLock = rwLock.writeLock(); // 写锁

    // 缓存更新可读取
    public static final Object get(String key) {
        rLock.lock();
        try {
            return map.get(key);
        } finally {
            rLock.unlock();
        }
    }

    public static final void set(String key, String value) {
        wLock.lock();
        try {
            map.put(key, value);
        } finally {
            wLock.unlock();
        }
    }
}
