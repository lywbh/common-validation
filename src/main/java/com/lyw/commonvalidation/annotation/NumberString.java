package com.lyw.commonvalidation.annotation;

import com.lyw.commonvalidation.validator.NumberStringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberStringValidator.class)
public @interface NumberString {

    String message() default "非法的数字类型";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
