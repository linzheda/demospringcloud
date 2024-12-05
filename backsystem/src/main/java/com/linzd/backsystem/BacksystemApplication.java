package com.linzd.backsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.linzd.backsystem.*", "com.linzd.basecore.*"})
@MapperScan("com.linzd.**.mapper")
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class BacksystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BacksystemApplication.class, args);
    }

}
