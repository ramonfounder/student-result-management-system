package io.shyftlabs.srms.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Custom validator to validate the date of birth of a student
 */
public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, Date> {

    // Define the age limit
    private int ageLimit;


    // Initialize the validator
    @Override
    public void initialize(DateOfBirthConstraint constraintAnnotation) {
        this.ageLimit = constraintAnnotation.ageLimit(); // Accessing the parameter from the annotation
    }

    // Validate the date of birth
    @Override
    public boolean isValid(Date dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        if (dateOfBirth == null) {
            return false;
        }

        // Convert Date to LocalDate
        LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();

        // Calculate the age
        int age = Period.between(birthDate, now).getYears();

        // Check if the age is at least ageLimit
        return age >= ageLimit;
    }
}