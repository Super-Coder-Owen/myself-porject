package com.vip.observer.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class EventListener {

    public Map<Enum, Event> events = new HashMap<Enum, Event>();

    public void addListener(Enum type, Object target, Method callback) {
        // 注册事件
        events.put(type, new Event(target, callback));
    }

    private void trigger(Event event) {
        event.setSource(this);
        event.setTime(new Date());
        try {
            event.getCallback().invoke(event.getTarget(),event);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void trigger(Enum type) {
        if (!this.events.containsKey(type)) {
            return;
        }
        this.trigger(this.events.get(type).setTrigger(type.toString()));
    }
}
