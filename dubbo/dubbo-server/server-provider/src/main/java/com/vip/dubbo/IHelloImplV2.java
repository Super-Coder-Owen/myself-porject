package com.vip.dubbo;

/**
 * 多版本
 */
public class IHelloImplV2 implements IHello {
    @Override
    public String sayHello(String msg) {
        return "hello version 2";
    }
}
