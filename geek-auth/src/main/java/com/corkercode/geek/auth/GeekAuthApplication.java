package com.corkercode.geek.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 或者@EnableCircuitBreaker
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class GeekAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekAuthApplication.class, args);
    }

}
