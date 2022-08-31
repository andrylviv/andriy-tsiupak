package com.epam.admissions_committee.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String city;
    private String region;
    private String school;
    private int ukLang;
    private int ukLiter;
    private int eng;
    private int algebra;
    private int informatics;
    private int geometry;
    private int ukHistory;
    private int phTraining;
    private int physics;
    private int eieUkLang;
    private int eieMath;
    private int eiePhysics;
}
