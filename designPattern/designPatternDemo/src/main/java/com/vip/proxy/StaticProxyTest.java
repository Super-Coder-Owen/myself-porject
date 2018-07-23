package com.vip.proxy;

/**
 *
 */
public class StaticProxyTest {
    public static void main(String[] args) {
         // 只能帮特定的人处理
         new Mather(new Son()).findLove();
    }
}
