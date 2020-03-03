package com.xiepanpan.gmall.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiepanpan
 * @Date: 2020/2/27
 * @Description: 配置当前系统的线程池信息
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 核心线程
     * @param poolProperties
     * @return
     */
    @Bean("mainThreadPoolExecutor")
    public ThreadPoolExecutor mainThreadPoolExecutor(PoolProperties poolProperties) {

        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>(poolProperties.getQueueSize());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolProperties.getCoreSize()
                , poolProperties.getMaximumPoolSize(), 10,
                TimeUnit.MINUTES, deque);
        return threadPoolExecutor;
    }

    /**
     * 非核心线程
     * @param poolProperties
     * @return
     */
    @Bean("otherThreadPoolExecutor")
    public ThreadPoolExecutor otherThreadPoolExecutor(PoolProperties poolProperties) {
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>(poolProperties.getQueueSize());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolProperties.getCoreSize()
                ,poolProperties.getMaximumPoolSize(),10,TimeUnit.MINUTES,deque);
        return threadPoolExecutor;
    }
}
