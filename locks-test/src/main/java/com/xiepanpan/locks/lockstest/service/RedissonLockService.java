package com.xiepanpan.locks.lockstest.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xiepanpan
 * @Date: 2020/2/24
 * @Description: redisson 分布式锁
 */
@Service
@Slf4j
public class RedissonLockService {

    @Autowired
    RedissonClient redissonClient;

    public String switchOff() throws InterruptedException {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("num");
        //还有5个人没走 trySetCount意思是从缓存取的num是0或者没有这个key 创建key设置值是5
        countDownLatch.trySetCount(5);
        log.info("我要拉闸了 下班了赶紧走 不要浪费公司的电");
        //大爷处于等待状态 看看谁还没走
        countDownLatch.await();
        log.info("拉闸断电！");
        return "拉闸断电！";
    }

    public String goHome() {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("num");
        countDownLatch.countDown();
        log.info("溜了溜了。。。");
        return "溜了溜了。。。";
    }
}
