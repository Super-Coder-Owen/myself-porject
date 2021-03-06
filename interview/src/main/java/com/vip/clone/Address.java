package com.vip.clone;

/**
 * @author owen
 * @description
 * @since 2018/11/6.
 */
public class Address implements Cloneable {
    private int cityCode;
    private String name;

    public Address() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Address(int cityCode, String name) {
        this.cityCode = cityCode;
        this.name = name;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Address{" +
                "cityCode=" + cityCode +
                ", name='" + name + '\'' +
                '}';
    }
}
