package com.xiepanpan.locks.lockstest.config;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: xiepanpan
 * @Date: 2020/2/20
 * @Description: jedis 配置类
 */
@Configuration
public class JedisConfig {

    @Bean
    public JedisPool jedisPoolConfig(RedisProperties properties) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        RedisProperties.Pool pool = properties.getJedis().getPool();

        //设置配置
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,properties.getHost(),properties.getPort());
        return jedisPool;
    }
}
