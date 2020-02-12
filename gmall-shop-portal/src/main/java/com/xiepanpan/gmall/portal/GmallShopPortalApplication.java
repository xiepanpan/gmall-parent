package com.xiepanpan.gmall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GmallShopPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallShopPortalApplication.class, args);
    }

}
