package com.epam.admissions_committee.controller.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String city;
    private String region;
    private String school;
    @Min(1)
    @Max(12)
    private int ukLang;
    @Min(1)
    @Max(12)
    private int ukLiter;
    @Min(1)
    @Max(12)
    private int eng;
    @Min(1)
    @Max(12)
    private int algebra;
    @Min(1)
    @Max(12)
    private int informatics;
    @Min(1)
    @Max(12)
    private int geometry;
    @Min(1)
    @Max(12)
    private int ukHistory;
    @Min(1)
    @Max(12)
    private int phTraining;
    @Min(1)
    @Max(12)
    private int physics;
    @Min(1)
    @Max(12)
    private int eieUkLang;
    @Min(1)
    @Max(12)
    private int eieMath;
    @Min(1)
    @Max(12)
    private int eiePhysics;
}
