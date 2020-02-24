package com.xiepanpan.locks.lockstest.controller;

import com.xiepanpan.locks.lockstest.service.RedisIncrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiepanpan
 * @Date: 2020/2/17
 * @Description:
 */
@RestController
public class HelloController {

    @Autowired
    RedisIncrService redisIncrService;

//    @GetMapping("/incr")
//    public String incr() {
//        redisIncrService.incr();
//        return "ok";
//    }

    @GetMapping("incr2")
    public String incr2() {
        redisIncrService.incrDistribute();
        return "ok";
    }
}
