package com.vip.spring5.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println(Thread.currentThread().getName() + "Hello World!");

        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                out.println(Thread.currentThread().getName() + "onComplete!");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                out.println(Thread.currentThread().getName() + "onTimeout!");
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                out.println(Thread.currentThread().getName() + "onError!");
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                out.println(Thread.currentThread().getName() + "onStartAsync!");
            }
        });
        asyncContext.start(() -> {
            out.println(Thread.currentThread().getName() + "service start!!!");
            asyncContext.complete();
        });


    }
}
