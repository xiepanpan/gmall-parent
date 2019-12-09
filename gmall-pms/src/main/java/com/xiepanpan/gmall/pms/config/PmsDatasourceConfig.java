package com.xiepanpan.gmall.pms.config;

import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;

/**
 * @author: xiepanpan
 * @Date: 2019/12/7
 * @Description: 配置sharding-jdbc
 */
@Configuration
public class PmsDatasourceConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        File file = ResourceUtils.getFile("classpath:sharding-jdbc.yml");
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(file);
        return dataSource;
    }
}
