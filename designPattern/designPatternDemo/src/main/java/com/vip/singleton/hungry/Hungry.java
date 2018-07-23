package com.vip.singleton.hungry;

/**
 * 饿汉式：在实例使用之前，不管你用不用，我都先new出来，避免线程安全问题
 */
public class Hungry {
    // 类加载顺序：先静态后动态，先属性后方法，先上后下
    private Hungry() {
    }

    public static final Hungry hungry = new Hungry();

    public static Hungry getInstance() {
        System.out.println(System.currentTimeMillis() + "->" + hungry);
        return hungry;
    }
}
