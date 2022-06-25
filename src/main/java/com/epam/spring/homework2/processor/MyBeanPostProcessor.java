package com.epam.spring.homework2.processor;

import com.epam.spring.homework2.abstraction.Validator;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof Validator) {
            ((Validator)bean).validate();
        }
        return bean;
    }
}
