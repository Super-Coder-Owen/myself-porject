package com.vip.strategy.interview.pay;

/**
 * 微信支付策略类
 * @author owen
 * @description
 * @since 2018/11/6.
 */
public class WechatIPay implements IPayStrategy {
    @Override
    public void pay() {
        System.out.println("wechat pay...");
    }
}
