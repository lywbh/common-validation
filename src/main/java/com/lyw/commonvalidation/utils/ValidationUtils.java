package com.lyw.commonvalidation.utils;

import com.lyw.commonvalidation.bo.CheckResult;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ValidationUtils implements ApplicationContextAware {

    private static Validator validator;

    public static CheckResult check(Object arg) {
        Set<ConstraintViolation<?>> failInfos = validate(arg);
        return failInfos.isEmpty() ? CheckResult.pass() : CheckResult.deny(failInfos);
    }

    private static Set<ConstraintViolation<?>> validate(Object arg) {
        Set<ConstraintViolation<?>> r = new HashSet<>();
        if (arg == null) {
            return r;
        }
        if (arg.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(arg); i++) {
                r.addAll(validate(Array.get(arg, i)));
            }
        } else if (arg instanceof Iterable) {
            for (Object item : (Iterable<?>) arg) {
                r.addAll(validate(item));
            }
            return r;
        }
        Validated validAnn = arg.getClass().getAnnotation(Validated.class);
        if (validAnn == null) {
            return r;
        }
        Set<ConstraintViolation<Object>> resultSet = validator.validate(arg, validAnn.value());
        if (resultSet != null && !resultSet.isEmpty()) {
            r.addAll(resultSet);
        }
        for (Field field : arg.getClass().getDeclaredFields()) {
            ReflectionUtils.makeAccessible(field);
            r.addAll(validate(ReflectionUtils.getField(field, arg)));
        }
        return r;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        validator = applicationContext.getBean(LocalValidatorFactoryBean.class);
    }

}
