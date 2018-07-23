package com.vip.decorator.passport.upgrede;

import com.vip.decorator.passport.old.ISignService;
import com.vip.decorator.passport.old.ResultMsg;

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
 * @since 2018/7/19.
 */
public class SignThirdServiceImpl implements ISignThirdService {

    private ISignService iSignService;

    public SignThirdServiceImpl(ISignService iSignService) {
        this.iSignService = iSignService;
    }

    public ResultMsg register(String userName, String pwd) {
        return iSignService.register(userName, pwd);
    }

    public ResultMsg login(String userName, String pwd) {
        return iSignService.login(userName, pwd);
    }

    public ResultMsg loginForQQ(String openId) {
        ResultMsg resultMsg = this.register("openId", null);
        resultMsg = this.login(openId, null);
        return resultMsg;
    }

    public ResultMsg loginForWechat(String openId) {
        ResultMsg resultMsg = this.register("openId", null);
        resultMsg = this.login(openId, null);
        return resultMsg;
    }
}
