package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.abstraction.Validator;

public class BeanF implements Validator {
    private String name;
    private int value;

    public BeanF(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void validate() {
        if ((name != null) && (value >= 0)) {
            System.out.println("valid F");
        }
    }

    @Override
    public String toString() {
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
