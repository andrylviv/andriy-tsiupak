package com.epam.admissions_committee.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<ValidPass, String> {
    @Override
    public void initialize(ValidPass contactNumber) {
    }

    @Override
    public boolean isValid(String passLength, ConstraintValidatorContext cxt) {
        return (passLength.length() > 8) && (passLength.length() < 12);
    }
}
