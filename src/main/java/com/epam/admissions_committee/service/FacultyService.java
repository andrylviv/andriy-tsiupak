package com.epam.admissions_committee.service;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import org.springframework.data.domain.Page;
import java.util.List;

public interface FacultyService {
    FacultyDto createFaculty(FacultyDto facultyDto, String language);
    FacultyDto getFaculty(String name);
    List<Faculty> listFaculty();
    FacultyDto updateFaculty(String name, FacultyDto facultyDto);
    void deleteFaculty(String name);
    Page<Faculty> listPagedAndSortingFacultyByStFoundPl(int page, int size);
    Page<Faculty> listPagedAndSortingFacultyByTotPl(int page, int size);
    List<Faculty> listPagedAndSortingFacultyByName(int page, int size, String lang);
}
