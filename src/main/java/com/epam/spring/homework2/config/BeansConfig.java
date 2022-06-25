package com.epam.spring.homework2.config;

import com.epam.spring.homework2.processor.MyBeanFactoryPostProcessor;
import com.epam.spring.homework2.processor.MyBeanPostProcessor;
import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

@Configuration
@Import(OtherConfig.class)
public class BeansConfig {
    @Bean
    public BeanA beanA() {
        return new BeanA("beanA", 1);
    }

    @Bean
    public BeanE beanE() {
        return new BeanE("beanE", 2);
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF("beanF", 3);
    }

    @Bean
    public static MyBeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new MyBeanFactoryPostProcessor();
    }

    @Bean
    public static MyBeanPostProcessor beanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
