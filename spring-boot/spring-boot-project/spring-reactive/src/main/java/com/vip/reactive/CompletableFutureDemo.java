package com.vip.reactive;

import java.util.concurrent.CompletableFuture;

/**
 *
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        println("当前线程");

        CompletableFuture.supplyAsync(() -> {
            println("第一步返回\"Hello\"");
            return "Hello"; // 第一步返回"Hello"
        }).thenApplyAsync(result -> {
            println("第二步在第一步的结果加\" World!\"");
            return result + " World!"; // 第二步在第一步的结果加" World!"
        }).thenAccept(CompletableFutureDemo::println) // 输出
                .whenComplete((v, exception) -> { // 返回值void，异常
                    println("执行结束");
                })
                .join() // 等待执行结束
        ;
        // 三段式编程
        // 业务执行
        // 执行完成
        // 异常处理
    }

    private static void println(String message) {
        System.out.printf("[线程:%s]%s\n", Thread.currentThread().getName(), message);
    }
}
