package com.lyw.commonvalidation.annotation;

import com.lyw.commonvalidation.validator.RegexParamValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegexParamValidator.class)
public @interface RegexParam {

    String regex();

    boolean required() default true;

    String message() default "非法参数，正则校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
