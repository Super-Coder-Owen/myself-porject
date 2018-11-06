package com.vip.strategy.interview;

/**
 * @author owen
 * @description
 * @since 2018/11/5.
 */
public class StrategyDemo {
    public static void main(String[] args) {
        Bill bill = new Bill();
        bill.addItem(new Item("JVM书籍", 5000));
        bill.addItem(new Item("皮蛋", 100));
        bill.pay(PaymentFactory.getPaymentMethod("debit"));
    }
}
