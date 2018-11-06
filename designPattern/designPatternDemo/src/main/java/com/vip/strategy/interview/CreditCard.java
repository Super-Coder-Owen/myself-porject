package com.vip.strategy.interview;

/**
 * @author owen
 * @description
 * @since 2018/11/5.
 */
public class CreditCard extends Card {
    @Override
    protected void executeTransaction(int cents) {
        System.out.println("do transaction with creditCard:" + cents);
    }

    @Override
    protected String getType() {
        return "credit";
    }
}
