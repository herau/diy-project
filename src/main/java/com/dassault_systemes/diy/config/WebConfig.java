package com.dassault_systemes.diy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("app.html");
        registry.addViewController("/login").setViewName("app.html");
        registry.addViewController("/tools").setViewName("app.html");
        registry.addViewController("/users").setViewName("app.html");
    }
}
