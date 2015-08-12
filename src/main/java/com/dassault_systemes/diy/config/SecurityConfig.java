package com.dassault_systemes.diy.config;

import com.dassault_systemes.diy.domain.Role;

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
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
            .antMatchers("/bower_components/**").permitAll()
            .anyRequest().fullyAuthenticated()
            .antMatchers("/account").hasRole(Role.USER.toString())
            .antMatchers("/admin").hasRole(Role.ADMIN.toString())
            .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("personalNumber")
                .permitAll()
                .successHandler((request, response, authentication) -> logger
                .info("Success login of {} with credentials : [{}]", authentication.getName(),
                      authentication.getAuthorities()))
                .defaultSuccessUrl("/account")
            .and()
            .logout().permitAll();
        // @formatter:on

        if (!security.isEnableCsrf()) {
            http.csrf().disable();
        }
    }
}
