package com.vip.observer;

import com.vip.observer.event.Event;

/**
 *
 */
public class Observer {
    public void advice(Event event) {
        System.out.println("打印日志->" + event);
    }
}

