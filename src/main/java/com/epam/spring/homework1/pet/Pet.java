package com.epam.spring.homework1.pet;

import com.epam.spring.homework1.abstraction.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Pet {
    @Autowired
    private List<Animal> animals;

    public void printPets(){
        for (Animal an : animals) {
            System.out.println(an.getAnimal());
        }
    }
}
