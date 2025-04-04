package io.github.jotabrc.ov_annotation_validator;

import io.github.jotabrc.ov_annotation_validator.config.ValidationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationAutoConfiguration {

    @Bean
    public ValidationConfig validationConfig() {
        return new ValidationConfig();
    }
}
