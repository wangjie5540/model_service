package com.digitforce.aip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring Boot Starter
 *
 * @author Frank Zhang
 */
@SpringBootApplication(scanBasePackages = {"com.digitforce"})
@MapperScan("com.digitforce.aip.mapper")
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableAsync
@EnableFeignClients(value = {"com.digitforce.bdp.operatex.core.api", "com.digitforce.manager"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
