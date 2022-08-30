package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.model.FacultyTranslate;
import com.epam.admissions_committee.model.Language;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FacultyMappingServiceImplTest {
    @InjectMocks
    FacultyMappingServiceImpl facultyMappingService;

    @Test
    void populateFacultyWithPresentFacultyDtoFieldsTest() {
        List<FacultyTranslate> facultyTranslates = new ArrayList<>();
        FacultyTranslate ft = FacultyTranslate.builder().facultyName("abc").language(Language.builder().language("en").build()).build();
        facultyTranslates.add(ft);
        Faculty faculty = Faculty.builder().stFundedPlaces(2).totPlaces(3).isEieMath(1).isEieUkLang(1).isEiePhysics(1).facultyTranslates(facultyTranslates).build();

        List<FacultyTranslate> facultyTranslatesDto = new ArrayList<>();
        FacultyTranslate ftDto = FacultyTranslate.builder().facultyName("abc").language(Language.builder().language("en").build()).build();
        facultyTranslatesDto.add(ftDto);
        FacultyDto facultyDto = FacultyDto.builder().stFundedPlaces(2).totPlaces(3).isEieMath(1).isEieUkLang(1).isEiePhysics(1).facultyTranslates(facultyTranslatesDto).build();

        List<FacultyTranslate> facultyTranslatesActual = new ArrayList<>();
        FacultyTranslate ftActual = FacultyTranslate.builder().facultyName("def").language(Language.builder().language("en").build()).build();
        facultyTranslatesActual.add(ftActual);
        Faculty actualFaculty = Faculty.builder().facultyTranslates(facultyTranslatesActual).build();

        actualFaculty = facultyMappingService.populateFacultyWithPresentFacultyDtoFields(actualFaculty, facultyDto);
        assertEquals(faculty, actualFaculty);
    }
}
