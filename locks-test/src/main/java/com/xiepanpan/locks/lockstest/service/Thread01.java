package com.xiepanpan.locks.lockstest.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: xiepanpan
 * @Date: 2020/2/27
 * @Description:
 */
@Slf4j
public class Thread01 extends Thread{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("创建线程异常：{}",e);
        }
        log.info("Thread01-当前线程：{}", Thread.currentThread());
    }
}
