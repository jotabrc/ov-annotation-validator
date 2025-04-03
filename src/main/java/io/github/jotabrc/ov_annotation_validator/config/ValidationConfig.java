package io.github.jotabrc.ov_annotation_validator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Properties configuration for patterns and messages.
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "validation")
public class ValidationConfig {

    private String text255Pattern;
    private String text255Message;

    private String text50Pattern;
    private String text50Message;

    private String passwordPattern;
    private String passwordMessage;

    private String phonePattern;
    private String phoneMessage;

    private String usernamePattern;
    private String usernameMessage;

    private String descriptionPattern;
    private String descriptionMessage;
}
