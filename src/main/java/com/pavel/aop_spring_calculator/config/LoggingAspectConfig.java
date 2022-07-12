package com.pavel.aop_spring_calculator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(LoggingAspectProperties.class)
@Configuration
public class LoggingAspectConfig {
}
