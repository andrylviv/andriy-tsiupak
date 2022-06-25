package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.abstraction.Validator;

public class BeanB implements Validator {
    private String name;
    private int value;

    public BeanB(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void validate() {
        if ((name != null) && (value >= 0)) {
            System.out.println("valid B");
        }
    }

    public void init() {
        System.out.println("init method beanB set by BFPP");
    }

    private void customInitMethod() {
        System.out.println("in beanB init method");
    }

    private void customDestroyMethod() {
        System.out.println("in beanB destroy method");
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
