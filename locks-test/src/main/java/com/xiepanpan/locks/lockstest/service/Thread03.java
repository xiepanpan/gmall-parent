package com.xiepanpan.locks.lockstest.service;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiepanpan
 * @Date: 2020/2/27
 * @Description:
 */
public class Thread03 implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return "OK";
    }
}
