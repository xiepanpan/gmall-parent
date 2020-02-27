package com.xiepanpan.locks.lockstest.service;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author: xiepanpan
 * @Date: 2020/2/27
 * @Description: 多线程测试类
 */
@Slf4j
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("主线程开始。。。");

//        Thread01 thread01 = new Thread01();
//        new Thread(thread01).start();

//        new Thread(new Thread02()).start();

//        FutureTask<String> task = new FutureTask<>(new Thread03());
//        new Thread(task).start();
//        String s = task.get();
//        log.info("主线程哈哈哈。。。。");
//        log.info("异步获取到的结果是：{}",s);

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
//        for (int i=0;i<10;i++) {
//            Thread thread = new Thread(() -> {
//                log.info("当前线程开始:{}", Thread.currentThread());
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                    int  j= 5/0;
//                } catch (InterruptedException e) {
//                    log.error("线程异常：{}", e);
//                }
//                log.info("当前线程结束: {}", Thread.currentThread());
//            });
//            ////给线程池提交任务
//            threadPool.submit(thread);
//        }

//        CompletableFuture.supplyAsync(()->{
//            log.info("当前线程开始: {}",Thread.currentThread());
//            String uuid = UUID.randomUUID().toString();
////            uuid+=10/0;
//            log.info("当前线程结束：{}",Thread.currentThread());
//            return uuid;
//        },threadPool).thenApply((r)->{
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                log.error("线程睡眠异常:{}",e);
//            }
//            log.info("上一步执行结果：{}",r);
//            int i = 10/0;
//            return r+10/0;
//        }).whenComplete((r,e)->{
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException ex) {
//                log.error("线程睡眠异常:{}",ex);
//            }
//            log.info("最终结果：{}",r);
//            log.error("异常信息：{}",e);
//        });
//        log.info("主线程结束......");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            log.info("查询商品基本数据...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.error("线程睡眠异常:{}",e);
            }
            return "华为";
        }, threadPool).whenComplete((r, e) -> {
            log.info("结果是：{}", r);
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            log.info("查询商品属性数据...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.error("线程睡眠异常:{}",e);
            }
            return "金色";
        }, threadPool).whenComplete((r, e) -> {
            log.info("结果是：{}", r);
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            log.info("查询商品营销数据...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                log.error("线程睡眠异常:{}",e);
            }
            return "满199减100";
        }, threadPool).whenComplete((r, e) -> {
            log.info("结果是：{}" + r);
        });
        CompletableFuture<Void> allOf = CompletableFuture.allOf(future1, future2, future3);
        //线程插队
        allOf.join();
        log.info("所有人都完事了：{}",future1.get()+future2.get()+future3.get());

    }

}
