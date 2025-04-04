package io.github.jotabrc.ov_annotation_validator.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ValidationConfig.class)
public class ValidationAutoConfiguration {
}
