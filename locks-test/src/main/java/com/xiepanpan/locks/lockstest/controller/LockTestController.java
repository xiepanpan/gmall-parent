package com.xiepanpan.locks.lockstest.controller;

import com.xiepanpan.locks.lockstest.service.RedissonLockService;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiepanpan
 * @Date: 2020/2/24
 * @Description:  Redisson 锁测试
 */
@RestController
public class LockTestController {

    @Autowired
    RedissonLockService redissonLockService;

    /**
     * 拉闸
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/switchOff")
    public String switchOff() throws InterruptedException {
        return redissonLockService.switchOff();
    }

    /**
     * 下班回家
     * @return
     */
    @GetMapping("/goHome")
    public String goHome() {
        return redissonLockService.goHome();
    }

}
