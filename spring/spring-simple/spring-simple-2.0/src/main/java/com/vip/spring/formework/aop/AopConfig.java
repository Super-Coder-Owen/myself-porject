package com.vip.spring.formework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 目标对象方法的一个增强
 * 由用自己实现的业务逻辑去增强
 * 配置文件目的：告诉Spring，哪些类的哪些方法需要增强，增强的内容是什么
 */
public class AopConfig {
    // 以目标对象作为key，把需要增强的代码作为value
    private Map<Method, Aspect> points = new HashMap<>();

    public void put(Method target, Object aspect, Method[] points) {
        this.points.put(target, new Aspect(aspect, points));
    }

    public Aspect get(Method target) {
        return this.points.get(target);
    }

    public boolean contains(Method target) {
        return this.points.containsKey(target);
    }

    // 对增强代码封装
    public class Aspect {
        private Object aspect; //会将LogAspect这个对象赋值给它
        private Method[] points;//会将LogAspect的before和after方法赋值进来

        public Aspect(Object aspect, Method[] points) {
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public void setAspect(Object aspect) {
            this.aspect = aspect;
        }

        public Method[] getPoints() {
            return points;
        }

        public void setPoints(Method[] points) {
            this.points = points;
        }
    }
}
