package com.ds.ce.diy.config;

import com.ds.ce.diy.web.EntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;

/**
 * override the default Spring boot access rules for Spring security
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(prefix = "security.basic", name = "enabled", matchIfMissing = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ConditionalOnWebApplication
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Inject
    private SecurityProperties security;

    @Inject
    private UserDetailsService authenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
            .antMatchers("/build/**").permitAll()
            .antMatchers("/lib/**").permitAll()
            .antMatchers("/profile/**/password").permitAll()
            .antMatchers(EntryPoint.TOKENS + "/**").permitAll()
            .anyRequest().fullyAuthenticated()
        .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("personalNumber")
                .successHandler((request, response, authentication) -> logger
                .info("Success login of {} with credentials : [{}]", authentication.getName(),
                      authentication.getAuthorities()))
                .defaultSuccessUrl("/")
        .and()
            .logout()
                .permitAll();
        // @formatter:on

        if (!security.isEnableCsrf()) {
            http.csrf().disable();
        }
    }
}
