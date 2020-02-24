package com.xiepanpan.locks.lockstest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;
import sun.font.Script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiepanpan
 * @Date: 2020/2/17
 * @Description:
 */
@Service
public class RedisIncrService {

    private Logger logger = LoggerFactory.getLogger(RedisIncrService.class);

    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    JedisPool jedisPool;


//    public synchronized void incr() {
//        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
//        String num = stringStringValueOperations.get("num");
//        if (num!=null) {
//            int i = Integer.parseInt(num);
//            i=i+1;
//            stringStringValueOperations.set("num", String.valueOf(i));
//        }
//    }
    public void incrDistribute() {
//        String token = UUID.randomUUID().toString();
//        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", token, 3, TimeUnit.SECONDS);
//        if (lock) {
//            ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
//            String num = stringStringValueOperations.get("num");
//            if (num !=null) {
//                Integer i = Integer.parseInt(num);
//                i=i+1;
//                stringStringValueOperations.set("num", i.toString());
//            }
//
//            //删除锁
//            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//            DefaultRedisScript<Long> script1 = new DefaultRedisScript<Long>(script,Long.class);
//            redisTemplate.execute(script1, Arrays.asList("lock"),token);
//            logger.info("删除锁成功：{}"+Thread.currentThread().getId());
//        }else {
//            incrDistribute();
//        }


            Jedis jedis = jedisPool.getResource();
            try {
                String token = UUID.randomUUID().toString();
                // 三秒过期
                String lock = jedis.set("lock", token, SetParams.setParams().ex(3).nx());
                if (lock!=null&&lock.equalsIgnoreCase("OK")) {
                    String num = jedis.get("num");
                    Integer i = Integer.parseInt(num);
                    i=i+1;
                    jedis.set("num", i.toString());

                    //删除锁
                    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                    jedis.eval(script, Collections.singletonList("lock"),Collections.singletonList(token));
                    logger.info("删除锁成功：{}"+Thread.currentThread().getId());
                }else {
                    try {
                        Thread.sleep(1000);
                        incrDistribute();
                    } catch (InterruptedException e) {
                        logger.error("线程睡眠异常：{}",e);
                    }
                }
            } finally {
                jedis.close();
            }
    }

}
