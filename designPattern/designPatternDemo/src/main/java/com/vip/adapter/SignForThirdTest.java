package com.vip.adapter;

import com.vip.adapter.passport.SignForThirdService;

/**
 * 适配器模式
 */
public class SignForThirdTest {
    public static void main(String[] args) {
        SignForThirdService service = new SignForThirdService();

        service.loginForQQ("openId");
    }
}
