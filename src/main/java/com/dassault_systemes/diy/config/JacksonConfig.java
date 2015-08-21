package com.dassault_systemes.diy.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * custom configuration for Jackson, in addition of spring.jackson.* properties
 */
@Configuration
public class JacksonConfig {

    /**
     * Enable the JSR 310 Jackson module to be able to manage Java 8 Date & Time
     *
     * @return Jackson Module which will be registered to the current Jackson objectMapper
     */
    @Bean
    Module jsr310Module() {
        return new JavaTimeModule();
    }

}
