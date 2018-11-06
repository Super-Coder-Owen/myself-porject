package com.vip.strategy.interview;

/**
 * @author owen
 * @description
 * @since 2018/11/5.
 */
public class Item {
    private String desc;
    private int cents;

    public Item(String desc, int cents) {
        this.desc = desc;
        this.cents = cents;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }
}
