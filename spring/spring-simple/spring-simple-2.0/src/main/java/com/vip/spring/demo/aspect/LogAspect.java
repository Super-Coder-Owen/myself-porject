package com.vip.spring.demo.aspect;

/**
 *
 */
public class LogAspect {
    public void before() {
        System.err.println(">>>>>>>>>>>>>>>>LogAspect before<<<<<<<<<<<<<<<<<");
    }

    public void after() {
        System.err.println(">>>>>>>>>>>>>>>>LogAspect after<<<<<<<<<<<<<<<<<");
    }
}

