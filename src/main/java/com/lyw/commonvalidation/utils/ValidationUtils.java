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
import java.lang.reflect.Field;
import java.util.*;

@Configuration
public class ValidationUtils implements ApplicationContextAware {

    private static Validator validator;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        validator = applicationContext.getBean(LocalValidatorFactoryBean.class);
    }

    public static CheckResult check(Object arg) {
        Set<ConstraintViolation<Object>> failInfos = validate(arg);
        if (failInfos == null || failInfos.isEmpty()) {
            return CheckResult.pass();
        }
        return CheckResult.deny(failInfos);
    }

    private static Set<ConstraintViolation<Object>> validate(Object arg) {
        Set<ConstraintViolation<Object>> r = new HashSet<>();
        if (arg == null) {
            return r;
        }
        if (arg instanceof Collection) {
            for (Object item : (Collection<Object>) arg) {
                r.addAll(validate(item));
            }
            return r;
        }
        Validated validAnn = arg.getClass().getAnnotation(Validated.class);
        if (validAnn == null) {
            return r;
        }
        Class<?>[] groupArrays = validAnn.value();
        Set<ConstraintViolation<Object>> resultSet = groupArrays.length == 0 ? validator.validate(arg) : validator.validate(arg, groupArrays);
        if (resultSet != null && !resultSet.isEmpty()) {
            r.addAll(resultSet);
        }
        for (Field field : arg.getClass().getDeclaredFields()) {
            ReflectionUtils.makeAccessible(field);
            r.addAll(validate(ReflectionUtils.getField(field, arg)));
        }
        return r;
    }

}
