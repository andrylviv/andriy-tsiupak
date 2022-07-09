package com.epam.admissions_committee.controller.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
public class FacultyDto{
    private int id;
    @NotBlank
    private String name;
    @Positive
    private int stFundedPlaces;
    @Positive
    private int totPlaces;
    @Min(1)
    @Max(12)
    private int isEieMath;
    @Min(1)
    @Max(12)
    private int isEieUkLang;
    @Min(1)
    @Max(12)
    private int isEiePhysics;
}
