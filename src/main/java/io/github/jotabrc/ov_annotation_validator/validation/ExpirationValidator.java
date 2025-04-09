package io.github.jotabrc.ov_annotation_validator.validation;

import io.github.jotabrc.ov_annotation_validator.annotation.ValidateExpiration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Check LocalDateTime expiration using hours in annotated fields.
 * Such as: @ValidateExpiration(fieldName = "expiration", expirationHours = 1, message = "Token expired")
 */
@Component
public class ExpirationValidator implements ConstraintValidator<ValidateExpiration, LocalDateTime> {

    private int expirationHours;
    private String errorMessage;

    @Override
    public void initialize(ValidateExpiration constraintAnnotation) {
        expirationHours = constraintAnnotation.expirationHours();
        errorMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(@NotNull @NotBlank LocalDateTime expiration, ConstraintValidatorContext constraintValidatorContext) {

        if (LocalDateTime.now().minusHours(expirationHours).isAfter(expiration)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
