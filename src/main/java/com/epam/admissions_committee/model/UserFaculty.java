package com.epam.admissions_committee.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFaculty {
    private int userId;
    private int facultyId;
}
