package com.vip.factory.abstr;

import com.vip.factory.Milk;

/**
 * 抽象工厂
 */
public abstract class AbstractFactory {
    public abstract Milk getTeLunSu();

    public abstract Milk getYiLi();

    public abstract Milk getMengNiu();
}
