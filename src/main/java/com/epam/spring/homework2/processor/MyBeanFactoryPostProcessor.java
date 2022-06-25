package com.epam.spring.homework2.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor begin");
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            if("beanB".equals(name)) {
                System.out.println("in if BFPP");
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
                beanDefinition.setInitMethodName("init");
            }
        }
        System.out.println("BeanFactoryPostProcessor end");
    }
}
