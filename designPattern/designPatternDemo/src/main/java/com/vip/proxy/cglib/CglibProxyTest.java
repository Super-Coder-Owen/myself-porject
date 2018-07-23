package com.vip.proxy.cglib;


/**
 *
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        // 只能帮特定的人处理
        Person obj = (Person) new HouseAgent().getInstance(Person.class);
        obj.findHouse();
        System.out.println(obj.getClass());
    }
}
