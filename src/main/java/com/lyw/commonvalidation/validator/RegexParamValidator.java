package com.lyw.commonvalidation.validator;

import com.lyw.commonvalidation.annotation.RegexParam;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class RegexParamValidator implements ConstraintValidator<RegexParam, String> {

    private Pattern pattern;
    private boolean required;

    @Override
    public void initialize(RegexParam constraintAnnotation) {
        pattern = Pattern.compile(constraintAnnotation.regex());
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !required || (s != null && pattern.matcher(s).matches());
    }

}
