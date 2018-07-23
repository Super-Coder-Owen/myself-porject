package com.vip.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
public class JdkProxyTest {
    public static void main(String[] args) throws IOException {
        // 只能帮特定的人处理
        Object obj = new HouseAgent().getInstance(new Person());
        PersonInterface person = (PersonInterface) obj;
        person.findHouse();
        System.out.println(obj.getClass()); //com.sun.proxy.$Proxy0

        //通过反编译工具可以查看源代码
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
        FileOutputStream fos = new FileOutputStream("D://$Proxy0.class");
        fos.write(bytes);
        fos.close();
    }
}
