package com.vip.decorator.passport.old;

/**
 * 　　　　　　　 ┏┓   ┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　  ┃　　　┃
 * 　　　　　　　  ┃　　　┃ + + + +
 * 　　　　　　　  ┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　  ┃　　　┃ + 　　　　神兽保佑,代码无BUG
 * 　　　　　　　  ┃　　　┃
 * 　　　　　　　  ┃　　　┃　　+
 * 　　　　　　　  ┃　 　　┗━━━┓ + +
 * 　　　　　　　  ┃ 　　　　　　　┣┓
 * 　　　　　　　  ┃ 　　　　　　　┏┛
 * 　　　　　　　  ┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　┃┫┫ ┃┫┫
 * 　　　　　　　　　┗┻┛ ┗┻┛+ + + +
 * Copyright (c) 2018 Mljia , All rights reserved.
 * http://www.mljia.cn/
 *
 * @author owen
 * @description
 * @since 2018/7/18.
 */
public class Member {
    private String id;
    private String name;
    private String pwd;
    private Integer age;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
