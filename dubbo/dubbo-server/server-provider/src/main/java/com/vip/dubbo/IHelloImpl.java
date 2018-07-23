package com.vip.dubbo;

/**
 *
 */
public class IHelloImpl implements  IHello{


    @Override
    public String sayHello(String msg) {
        return "Hello"+msg;
    }
}
