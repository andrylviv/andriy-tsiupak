package com.epam.admissions_committee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Statement {
    private int userId;
    private int facultyId;
    private int stFonPl;
    private int nonStFonPl;
}
