package com.vip.strategy.interview.pay;

/**
 * 定义上下文，选择支付方式
 * @author owen
 * @description
 * @since 2018/11/6.
 */
public class ContextPayStrategy {
    private IPayStrategy iPayStrategy;

    public ContextPayStrategy(IPayStrategy iPayStrategy) {
        this.iPayStrategy = iPayStrategy;
    }

    public void pay() {
        iPayStrategy.pay();
    }
}
