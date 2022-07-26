package com.epam.admissions_committee.controller.dto;

import com.epam.admissions_committee.model.FacultyTranslate;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Builder
public class FacultyDto{
    private Long facultyId;
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
    private List<FacultyTranslate> facultyTranslates;
}
