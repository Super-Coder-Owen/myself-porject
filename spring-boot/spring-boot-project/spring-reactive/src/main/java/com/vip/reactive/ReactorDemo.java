package com.vip.reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 *
 */
public class ReactorDemo {
    public static void main(String[] args) throws InterruptedException {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9) // 直接执行
                .filter(v -> v % 2 == 1) // ->获取奇数
                .map(v -> v - 1) //奇数变偶数
                .reduce(Integer::sum) // 聚合操作
                //.subscribeOn(Schedulers.immediate())  // 线程:main
                //.subscribeOn(Schedulers.elastic()) // 线程:elastic-2
                .subscribeOn(Schedulers.parallel()) // 线程:parallel-1
                .subscribe(ReactorDemo::println); // 订阅才执行

        Thread.sleep(1000);
    }

    private static void println(Object message) {
        System.out.printf("[线程:%s]%s\n", Thread.currentThread().getName(), message);
    }
}
