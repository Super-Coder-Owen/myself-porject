package com.vip.strategy.interview.pay;

/**
 * 测试类
 * @author owen
 * @description
 * @since 2018/11/6.
 */
public class PayTest {
    public static void main(String[] args) {
        ContextPayStrategy contextPayStrategy = new ContextPayStrategy(new WechatPay());
        contextPayStrategy.pay();
    }
}
