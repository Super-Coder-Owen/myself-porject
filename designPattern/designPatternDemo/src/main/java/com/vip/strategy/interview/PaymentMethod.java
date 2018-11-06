package com.vip.strategy.interview;

/**
 * 支付方式
 *
 * @author owen
 * @description
 * @since 2018/11/5.
 */
public interface PaymentMethod {
    void pay(int cents);
}
