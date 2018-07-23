package com.vip.observer;

import com.vip.observer.event.EventListener;

/**
 *
 */
public class SourceObject extends EventListener {
    public void add() {
        System.out.println("增加");
        trigger(EventType.ON_ADD);

    }

    public void remove() {
        System.out.println("移除");
        trigger(EventType.ON_REMOVE);
    }
}
