package com.lyw.commonvalidation.config;

import org.apache.bval.jsr.ApacheValidationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationAutoConfiguration {

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(ApacheValidationProvider.class);
        return localValidatorFactoryBean;
    }

}
