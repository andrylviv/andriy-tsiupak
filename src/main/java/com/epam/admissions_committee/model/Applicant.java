package com.epam.admissions_committee.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Applicant {
    private User applicantId;
    private double mark;
}
