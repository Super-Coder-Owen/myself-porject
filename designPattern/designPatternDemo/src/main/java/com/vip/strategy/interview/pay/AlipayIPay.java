package com.vip.strategy.interview.pay;

/**
 * 支付宝策略类
 * @author owen
 * @description
 * @since 2018/11/6.
 */
public class AlipayIPay implements IPayStrategy {
    @Override
    public void pay() {
        System.out.println("alipay pay...");
    }
}
