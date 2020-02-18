package com.xiepanpan.locks.lockstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author: xiepanpan
 * @Date: 2020/2/17
 * @Description:
 */
@Service
public class RedisIncrService {

    @Autowired
    StringRedisTemplate redisTemplate;

    public synchronized void incr() {
        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        String num = stringStringValueOperations.get("num");
        if (num!=null) {
            int i = Integer.parseInt(num);
            i=i+1;
            stringStringValueOperations.set("num", String.valueOf(i));
        }
    }

}
