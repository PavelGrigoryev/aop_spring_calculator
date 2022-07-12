package com.pavel.aop_spring_calculator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aspect")
public class LoggingAspectProperties {
    private String path;
}
