package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.abstraction.Validator;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE implements Validator {
    private String name;
    private int value;

    public BeanE(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void validate() {
        if ((name != null) && (value >= 0)) {
            System.out.println("valid E");
        }
    }

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("inside beanE @PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("inside beanE @PreDestroy");
    }
}
