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
 * @author owen
 * @description
 * @since 2018/11/1.
 */
public class ServerHandler implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            String expression = null;
            String result = null;
            while (true) {
                if ((expression = br.readLine()) == null) {
                    break;
                }
                LOGGER.info("服务端收到消息：" + expression);
                result = Calculator.cal(expression);
                out.println(result);
            }
        } catch (IOException e) {
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
