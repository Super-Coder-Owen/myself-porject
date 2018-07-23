package com.vip.dubbo;

/**
 *
 */
public class IHello2Impl implements  IHello{


    @Override
    public String sayHello(String msg) {
        return "Hello server 2->"+msg;
    }
}
