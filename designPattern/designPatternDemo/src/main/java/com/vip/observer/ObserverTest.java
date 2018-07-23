package com.vip.observer;

import com.vip.observer.event.Event;

import java.lang.reflect.Method;

/**
 *
 */
public class ObserverTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Observer observer = new Observer();
        Method advice = Observer.class.getMethod("advice", new Class[]{Event.class});

        SourceObject sourceObject = new SourceObject();
        sourceObject.addListener(EventType.ON_ADD, observer, advice);
        sourceObject.addListener(EventType.ON_REMOVE, observer, advice);

        sourceObject.add();
        sourceObject.remove();
    }
}
