package com.vip.chapter1.thread.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 通过异步的线程执行某一个逻辑，在逻辑的运行过程中
 * 最终运行结束后，希望在当前主线程里面拿到子线程执行的结果
 */
public class CallableDemo implements Callable {
    @Override
    public Object call() throws Exception {
        return "callable test...";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        CallableDemo callableDemo = new CallableDemo();
        Future future = executorService.submit(callableDemo);
        // 阻塞
        System.out.println(future.get());
        executorService.shutdown();
    }
}
