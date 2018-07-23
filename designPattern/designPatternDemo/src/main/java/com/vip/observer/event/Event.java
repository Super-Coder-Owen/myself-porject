package com.vip.observer.event;

import java.lang.reflect.Method;
import java.util.Date;

/**
 *
 */
public class Event {
    // 事件源
    private Object source;
    // 目标
    private Object target;
    // 回调
    private Method callback;
    // 触发
    private String trigger;
    // 时间
    private Date time;

    public Event(Object target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    public Event(Object source, Object target, Method callback, String trigger) {
        this.source = source;
        this.target = target;
        this.callback = callback;
        this.trigger = trigger;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getCallback() {
        return callback;
    }

    public void setCallback(Method callback) {
        this.callback = callback;
    }

    public String getTrigger() {
        return trigger;
    }

    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "source=" + source +
                ", target=" + target +
                ", callback=" + callback +
                ", trigger='" + trigger + '\'' +
                '}';
    }
}
