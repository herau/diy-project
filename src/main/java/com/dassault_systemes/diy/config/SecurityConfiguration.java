package com.dassault_systemes.diy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.sql.DataSource;

/**
 * override the default Spring boot access rules for Spring security
 */
@Configuration
@EnableWebMvcSecurity
@ConditionalOnWebApplication
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties security;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/bower_components/bootstrap/**").permitAll().antMatchers("/css/**")
            .permitAll().anyRequest().fullyAuthenticated().antMatchers("/", "/account").hasRole("USER")
            .antMatchers("/admin").hasRole("ADMIN").and().formLogin().loginPage("/login").permitAll().and().logout()
            .permitAll();
    }
}
