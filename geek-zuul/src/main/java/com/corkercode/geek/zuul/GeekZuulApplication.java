package com.corkercode.geek.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class GeekZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekZuulApplication.class, args);
    }

}
