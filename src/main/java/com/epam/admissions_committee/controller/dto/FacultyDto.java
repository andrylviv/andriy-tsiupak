package com.epam.admissions_committee.controller.dto;

import com.epam.admissions_committee.model.FacultyTranslate;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@Builder
public class FacultyDto{
    private Long facultyId;
    @PositiveOrZero
    private int stFundedPlaces;
    @PositiveOrZero
    private int totPlaces;
    @Min(0)
    @Max(1)
    private int isEieMath;
    @Min(0)
    @Max(1)
    private int isEieUkLang;
    @Min(0)
    @Max(1)
    private int isEiePhysics;
    private List<FacultyTranslate> facultyTranslates;
}
