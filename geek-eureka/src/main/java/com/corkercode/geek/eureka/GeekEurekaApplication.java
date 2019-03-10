package com.corkercode.geek.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GeekEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekEurekaApplication.class, args);
    }

}
