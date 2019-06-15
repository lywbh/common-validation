package com.lyw.commonvalidation.validator;

import com.lyw.commonvalidation.annotation.NumberString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberStringValidator implements ConstraintValidator<NumberString, String> {

    @Override
    public boolean isValid(String numberString, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Double.valueOf(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
