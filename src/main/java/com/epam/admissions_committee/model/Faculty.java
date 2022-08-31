package com.epam.admissions_committee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    private Long facultyId;
    private int stFundedPlaces;
    private int totPlaces;
    private int isEieMath;
    private int isEieUkLang;
    private int isEiePhysics;
    private List<FacultyTranslate> facultyTranslates;
    private Instant writtenOn;
    Set<User> users;
    List<Statement> statements;
}
