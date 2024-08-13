package com.sample.genzschool.customAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import com.sample.genzschool.Validations.PasswordStrengthValidator;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class )
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "Please choose strong password!!";
}
