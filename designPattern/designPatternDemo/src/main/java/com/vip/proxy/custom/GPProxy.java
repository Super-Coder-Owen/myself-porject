package com.vip.proxy.custom;

import java.lang.reflect.Method;

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
public class GPProxy {
    private static final String rn = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader, GPInvocationHandler invocationHandler) {
        // 1.动态生成源代码，.java文件

        // 2.java文件输出磁盘

        // 3.把生成的.java文件编译成.class文件

        // 4.编译成的.class文件加载到jvm中

        // 5.返回字节码重组以后的新代理对象
        return null;
    }

    public static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.vip.proxy.custom;" + rn);
        sb.append("import com.vip.proxy.jdk.Person;" + rn);
        sb.append("public class $Proxy0 implements " + rn);
        sb.append(interfaces[0]);
        sb.append(" {" + rn);
            sb.append("GPInvocationHandler h;" + rn);
            sb.append("public $Proxy0(GPInvocationHandler h){" + rn);
            sb.append("this.h=h;" + rn);
            sb.append("}" + rn);
            for(Method m:interfaces[0].getMethods()){

            }

        sb.append(" }");
        return sb.toString();
    }
}
