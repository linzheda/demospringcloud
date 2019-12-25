package com.linzd.zuulapigateway;

import com.linzd.zuulapigateway.filters.RateLimitZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApiGatewayApplication.class, args);
    }

    @Bean
    public RateLimitZuulFilter rateLimitZuulFilter() {
        return new RateLimitZuulFilter();
    }
}
