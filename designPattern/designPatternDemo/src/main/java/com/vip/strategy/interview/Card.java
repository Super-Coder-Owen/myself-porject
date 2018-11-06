package com.vip.strategy.interview;

/**
 * @author owen
 * @description
 * @since 2018/11/5.
 */
public abstract class Card implements PaymentMethod {
    @Override
    public void pay(int cents) {
        System.out.println("use " + getType() + "-> payed " + cents);
        executeTransaction(cents); // 具体执行支付操作
    }

    protected abstract void executeTransaction(int cents);

    protected abstract String getType();
}
