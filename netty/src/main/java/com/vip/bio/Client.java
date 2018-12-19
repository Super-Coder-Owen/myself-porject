package com.vip.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.out;

/**
 * 客户端
 *
 * @author owen
 * @description
 * @since 2018/11/1.
 */
public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    // 默认服务端端口号
    private static final int DEFAULT_SERVER_PORT = 7777;

    // 服务端IP
    private static final String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String expression) {
        send(DEFAULT_SERVER_PORT, expression);
    }

    public static void send(int port, String expression) {
        LOGGER.info("算数表达式:" + expression);
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            out.println(expression);
            LOGGER.info("结果为：" + br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                br = null;
            }
            if (pw != null) {
                pw.close();
                pw = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
