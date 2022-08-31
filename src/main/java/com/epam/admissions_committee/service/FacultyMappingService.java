package com.epam.admissions_committee.service;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;

public interface FacultyMappingService {
    Faculty populateFacultyWithPresentFacultyDtoFields(Faculty faculty, FacultyDto facultyDto);
}
