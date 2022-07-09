package com.epam.admissions_committee.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacultyDto{
    private int id;
    private String name;
    private int stFundedPlaces;
    private int totPlaces;
    private String langName;
    private int isEieMath;
    private int isEieUkLang;
    private int isEiePhysics;
}
