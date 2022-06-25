package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.abstraction.Validator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanA implements InitializingBean, DisposableBean, Validator {
    private String name;
    private int value;

    public BeanA(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void validate() {
        if ((name != null) && (value >= 0)) {
            System.out.println("valid A");
        }
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("inside beanA InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() {
        System.out.println("inside beanA DisposableBean.destroy()");
    }
}
