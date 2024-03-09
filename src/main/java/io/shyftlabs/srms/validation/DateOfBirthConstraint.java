package io.shyftlabs.srms.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom constraint annotation to validate the date of birth of a student
 */
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateOfBirthConstraint {
    String message() default "The student must be at least 10 years old.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // Define additional parameters here if needed
    int ageLimit() default 10; // Parameter to specify the age limit
}