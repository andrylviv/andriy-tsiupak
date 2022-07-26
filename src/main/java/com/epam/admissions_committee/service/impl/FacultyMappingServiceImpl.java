package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.model.FacultyTranslate;
import com.epam.admissions_committee.service.FacultyMappingService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyMappingServiceImpl implements FacultyMappingService {
    @Override
    public Faculty populateFacultyWithPresentFacultyDtoFields(Faculty faculty, FacultyDto facultyDto) {
        faculty.setStFundedPlaces(facultyDto.getStFundedPlaces());
        faculty.setTotPlaces(facultyDto.getTotPlaces());
        faculty.setIsEieMath(facultyDto.getIsEieMath());
        faculty.setIsEieUkLang(facultyDto.getIsEieUkLang());
        faculty.setIsEiePhysics(facultyDto.getIsEiePhysics());
        List<FacultyTranslate> facultyTranslates = faculty.getFacultyTranslates();
        List<FacultyTranslate> facultyTranslatesDTO = facultyDto.getFacultyTranslates();
        if (facultyTranslatesDTO != null) {
            for (FacultyTranslate ft : facultyTranslates) {
                for (FacultyTranslate ftDTO : facultyTranslatesDTO) {
                    populateFacultyTranslateWithPresentFacultyTranslateFields(ft, ftDTO);
                }
            }
        }
        return faculty;
    }

    private void populateFacultyTranslateWithPresentFacultyTranslateFields(FacultyTranslate facultyTranslate, FacultyTranslate facultyTranslateDTO) {
        if ((facultyTranslateDTO.getLanguage() != null) && (facultyTranslate.getLanguage().getLanguage().equals(facultyTranslateDTO.getLanguage().getLanguage()))) {
            facultyTranslate.setFacultyName(facultyTranslateDTO.getFacultyName());
        }
    }
}
