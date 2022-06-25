package com.epam.spring.homework2;

import com.epam.spring.homework2.config.BeansConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(((AnnotationConfigApplicationContext) context).getBeanDefinition(beanDefinitionName));
        }
        ((AnnotationConfigApplicationContext) context).close();
    }
}
