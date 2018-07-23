package com.vip.decorator;

import com.vip.decorator.passport.old.SignServiceImpl;
import com.vip.decorator.passport.upgrede.SignThirdServiceImpl;

/**
 * 装饰者模式
 */
public class DecoratorTest {
    public static void main(String[] args) {
        // 为了某个实现类在不修改原始类的基础上进行动态地覆盖或者增加方法，该实现保持跟原有类的层级关系
        // 采用装饰模式
       /* InputStream in = null;
        FilterInputStream fs = new DataInputStream(in);*/

        SignThirdServiceImpl signThirdService = new SignThirdServiceImpl(new SignServiceImpl());
        signThirdService.loginForQQ("openid");
    }
}
