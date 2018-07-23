package com.vip.factory.simple;

/**
 *
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {

        //System.out.print(new TeLunSu().getName());

        System.out.println(new SimpleFactory().getMilk("特仑苏"));
    }
}
