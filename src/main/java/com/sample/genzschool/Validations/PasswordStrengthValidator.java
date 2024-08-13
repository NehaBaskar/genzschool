package com.sample.genzschool.Validations;

import com.sample.genzschool.customAnnotations.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator,String> {

    List<String> weakPasswords;
    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = Arrays.asList("1234","qwerty","Qwerty","password");
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext validatorContext) {
        return passwordField != null && (!weakPasswords.contains(passwordField));
    }
}
