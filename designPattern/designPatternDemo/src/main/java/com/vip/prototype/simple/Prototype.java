package com.vip.prototype.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式
 */
public class Prototype implements Cloneable{
    public String name;
    public List<String> list = new ArrayList();

    /**
     * 浅复制，复制了引用地址,引用地址变了，值会变
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
