package com.vip.clone;

/**
 * @author owen
 * @description
 * @since 2018/11/6.
 */
public class User implements Cloneable {
    private String name;
    private int age;
    private Address address;

    public User() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        User user = (User) super.clone();
        user.setAddress((Address) user.getAddress().clone());
        return user;
    }

    public User(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
