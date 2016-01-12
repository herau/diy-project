package com.ds.ce.diy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

//TODO remove when we'll switch to Hibernate5 / Spring 1.4
@EntityScan(basePackageClasses = {DiyApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
public class DiyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiyApplication.class, args);
    }
}
