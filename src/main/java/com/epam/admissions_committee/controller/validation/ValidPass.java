package com.epam.admissions_committee.controller.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPass {
    String message() default "Invalid password length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
