package com.xiepanpan.gmall.portal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xiepanpan
 * @Date: 2020/2/27
 * @Description: 线程池配置参数
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "gmall.pool")
public class PoolProperties {
    private Integer coreSize;
    private Integer maximumPoolSize;
    private Integer queueSize;
}
