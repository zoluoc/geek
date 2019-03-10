package com.corkercode.geek.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class GeekGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekGatewayApplication.class, args);
    }

}
