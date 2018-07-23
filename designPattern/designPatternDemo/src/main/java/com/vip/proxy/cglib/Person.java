package com.vip.proxy.cglib;

import com.vip.proxy.jdk.PersonInterface;

/**
 *
 */
public class Person implements PersonInterface{
    public void findHouse(){
        System.out.println("person find house...");
    }
}
