package com.vip.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 *
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-client.xml");

       /* for (int i = 0; i < 10; i++) {
            IHello hello = (IHello) context.getBean("iHello");
            System.out.println(hello.sayHello("owen++++++++++++++++++++++++++++++++++++++"));
            Thread.sleep(1000);
        }*/

  /*      IHello hello = (IHello) context.getBean("iHello");
        System.out.println(hello.sayHello("owen++++++++++++++++++++++++++++++++++++++"));*/
 /*       System.in.read();*/

         /*Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myProtocol");
        System.out.println(protocol.getDefaultPort());*/

        /*Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getDefaultExtension();
        System.out.println(protocol.getDefaultPort());*/


        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
    }
}
