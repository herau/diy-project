package com.ds.ce.diy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
public class DiyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiyApplication.class, args);
    }
}
