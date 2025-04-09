package io.github.jotabrc.ov_annotation_validator.annotation;

import io.github.jotabrc.ov_annotation_validator.validation.ExpirationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for LocalDateTime fields validation.
 * Required fields are expirationHours.
 */
@Constraint(validatedBy = ExpirationValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateExpiration {
    String message() default "Token expired";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    int expirationHours();
}
