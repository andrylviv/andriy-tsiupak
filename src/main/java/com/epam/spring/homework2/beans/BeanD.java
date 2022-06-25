package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.abstraction.Validator;

public class BeanD implements Validator {
    private String name;
    private int value;

    public BeanD(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void validate() {
        if ((name != null) && (value >= 0)) {
            System.out.println("valid D");
        }
    }

    private void customInitMethod() {
        System.out.println("in beanD init method");
    }

    private void customDestroyMethod() {
        System.out.println("in beanD destroy method");
    }

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
