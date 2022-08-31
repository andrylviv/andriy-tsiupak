package com.epam.admissions_committee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
    private Long id;
    private User user;
    private Faculty faculty;
    private int facultyId;
    private int stFonPl;
    private int nonStFonPl;
}
