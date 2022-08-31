package com.epam.admissions_committee.service.exeption;

public class EntityNotFoundException extends Exception{
    private int detail;
    EntityNotFoundException(int a) {
        detail = a;
    }
}
