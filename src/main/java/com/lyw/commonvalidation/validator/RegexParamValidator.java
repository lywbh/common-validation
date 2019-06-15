package com.lyw.commonvalidation.validator;

import com.lyw.commonvalidation.annotation.RegexParam;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class RegexParamValidator implements ConstraintValidator<RegexParam, String> {

    private String regex;
    private boolean required;

    @Override
    public void initialize(RegexParam constraintAnnotation) {
        regex = constraintAnnotation.regex();
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !required || Pattern.compile(regex).matcher(s).matches();
    }

}
