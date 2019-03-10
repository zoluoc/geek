package com.corkercode.geek.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class GeekZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeekZipkinApplication.class, args);
    }

}
