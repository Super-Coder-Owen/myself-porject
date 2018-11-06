package com.vip.strategy.interview;

/**
 * @author owen
 * @description
 * @since 2018/11/5.
 */
public class PaymentFactory {
    public static PaymentMethod getPaymentMethod(String type) {
        switch (type) {
            case "credit":
                return new CreditCard();
            case "debit":
                return new DeBitCard();
            default:
                throw new RuntimeException("can't find type");
        }
    }
}
