package com.ds.ce.diy.Config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @ConditionalOnMissingClass({"JavaMailSender"})
    @Bean
    public JavaMailSenderImpl JavaMailSender() {
        return new JavaMailSenderImpl();
    }

}
