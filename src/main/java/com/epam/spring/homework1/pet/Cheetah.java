package com.epam.spring.homework1.pet;

import com.epam.spring.homework1.abstraction.Animal;
import org.springframework.stereotype.Component;

@Component
public class Cheetah implements Animal {
    @Override
    public String getAnimal() {
        return "Cheetah";
    }
}
