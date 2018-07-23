package com.vip.factory.simple;

import com.vip.factory.MengNiu;
import com.vip.factory.Milk;
import com.vip.factory.TeLunSu;
import com.vip.factory.YiLi;

/**
 *
 */
public class SimpleFactory {
    public Milk getMilk(String name) {
        if ("特仑苏".equals(name)) {
            return new TeLunSu();
        } else if ("伊利".equals(name)) {
            return new YiLi();
        } else if ("蒙牛".equals(name)) {
            return new MengNiu();
        }
        return null;
    }
}
