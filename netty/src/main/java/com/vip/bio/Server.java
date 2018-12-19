package com.vip.bio;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Bio服务端
 *
 * @author owen
 * @description
 * @since 2018/11/1.
 */
public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    // 默认端口号
    private static final int DEFAULT_PORT = 7777;

    // 单例的ServerSocket
    private static ServerSocket serverSocket;

    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int port) throws IOException {
        if (null != serverSocket) {
            return;
        }
        try {
            serverSocket = new ServerSocket(port);
            LOGGER.info("服务端已启动，端口号:" + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } finally {
            if (serverSocket != null) {
                LOGGER.info("服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
