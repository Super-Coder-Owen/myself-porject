package com.vip.clone;

/**
 * 测试类
 *
 * @author owen
 * @description
 * @since 2018/11/6.
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User("owen", 27, new Address(8, "街"));
        User user1 = (User) user.clone();
        user1.getAddress().setCityCode(9);
        System.out.println(user);
        System.out.println(user1);
    }
}
