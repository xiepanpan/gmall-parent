package com.xiepanpan.gmall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xiepanpan
 * @Date: 2020/3/2
 * @Description: sso配置类
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sso.server")
public class SsoConfig {

    private String url;
    private String loginPath;
}
