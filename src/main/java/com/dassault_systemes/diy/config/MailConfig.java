package com.dassault_systemes.diy.config;

import com.dassault_systemes.diy.settings.AppSettings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import javax.inject.Inject;

@Configuration
public class MailConfig {

    @Inject
    AppSettings settings;

    @Bean
    public SimpleMailMessage template() {
        SimpleMailMessage template = new SimpleMailMessage();

        template.setSubject(settings.getEmail().getSubject());

        return template;
    }
}
