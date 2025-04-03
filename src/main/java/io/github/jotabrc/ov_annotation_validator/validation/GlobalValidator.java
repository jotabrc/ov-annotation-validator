package io.github.jotabrc.ov_annotation_validator.validation;

import io.github.jotabrc.ov_annotation_validator.annotation.ValidateField;
import io.github.jotabrc.ov_annotation_validator.config.ValidationConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * GlobalValidator for common fields.
 */
@Component
public class GlobalValidator implements ConstraintValidator<ValidateField, String> {

    @Autowired
    private ValidationConfig validationConfig;

    private String pattern;
    private String errorMessage;

    @Override
    public void initialize(ValidateField constraintAnnotation) {
        String fieldName = constraintAnnotation.fieldName();
        switch (fieldName) {
            case "text255" -> {
                this.pattern = validationConfig.getText255Pattern();
                this.pattern = validationConfig.getText255Message();
            }
            case "text50" -> {
                this.pattern = validationConfig.getText50Pattern();
                this.errorMessage = validationConfig.getText50Message();
            }
            case "password" -> {
                this.pattern = validationConfig.getPasswordPattern();
                this.errorMessage = validationConfig.getPasswordMessage();
            }
            case "phone" -> {
                this.pattern = validationConfig.getPhonePattern();
                this.errorMessage = validationConfig.getPhoneMessage();
            }
            case "username" -> {
                this.pattern = validationConfig.getUsernamePattern();
                this.errorMessage = validationConfig.getUsernameMessage();
            }
            case "description" -> {
                this.pattern = validationConfig.getDescriptionPattern();
                this.errorMessage = validationConfig.getDescriptionMessage();
            }
            default -> throw new IllegalArgumentException("Unsupported field " + fieldName);
        }
    }

    @Override
    public boolean isValid(@NotNull @NotBlank String string, ConstraintValidatorContext constraintValidatorContext) {

        if (!string.matches(pattern)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
