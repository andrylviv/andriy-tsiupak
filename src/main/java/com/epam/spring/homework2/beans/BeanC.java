package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.abstraction.Validator;

public class BeanC implements Validator {
    private String name;
    private int value;

    public BeanC(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void validate() {
        if ((name != null) && (value >= 0)) {
            System.out.println("valid C");
        }
    }

    private void customInitMethod() {
        System.out.println("in beanC init method");
    }

    private void customDestroyMethod() {
        System.out.println("in beanC destroy method");
    }

    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
