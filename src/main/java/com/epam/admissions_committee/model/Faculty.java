package com.epam.admissions_committee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    private int id;
    private String name;
    private int stFundedPlaces;
    private int totPlaces;
    private String langName;
    private int isEieMath;
    private int isEieUkLang;
    private int isEiePhysics;
    private Instant writtenOn;
}
