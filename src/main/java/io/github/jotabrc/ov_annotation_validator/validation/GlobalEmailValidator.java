package io.github.jotabrc.ov_annotation_validator.validation;

import io.github.jotabrc.ov_annotation_validator.annotation.ValidateEmail;
import io.github.jotabrc.ov_annotation_validator.config.ValidationConfig;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Global email validation using Apache Commons Validator.
 */
@Component
public class GlobalEmailValidator implements ConstraintValidator<ValidateEmail, String> {

    @Autowired
    private ValidationConfig validationConfig;
    EmailValidator globalEmailValidator = EmailValidator.getInstance();

    private String errorMessage;

    @Override
    public void initialize(ValidateEmail constraintAnnotation) {
        this.errorMessage = validationConfig.getDescriptionMessage();
    }

    @Override
    public boolean isValid(@NotNull @NotBlank String string, ConstraintValidatorContext constraintValidatorContext) {

        if (!globalEmailValidator.isValid(string)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
