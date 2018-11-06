package com.vip.strategy.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author owen
 * @description
 * @since 2018/11/5.
 */
public class Bill {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public int getSumCents() {
        return items.stream().mapToInt(item -> item.getCents()).sum();
    }

    public void pay(PaymentMethod paymentMethod) {
        paymentMethod.pay(getSumCents());
    }
}
