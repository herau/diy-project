package com.ds.ce.diy.config;

import com.ds.ce.diy.web.EntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/tools").setViewName("app");
        //registry.addViewController("/users").setViewName("app");
        //registry.addViewController("/profile").setViewName("app");
        registry.addViewController(String.format(EntryPoint.PROFILE_PWD, "**")).setViewName("app");
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/admin").setViewName("admin.html");
    }
}
