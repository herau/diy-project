package com.dassault_systemes.diy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Enable entities Auditing behavior
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfig {}
