package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.model.FacultyTranslate;
import com.epam.admissions_committee.model.Language;
import com.epam.admissions_committee.repository.FacultyRepository;
import com.epam.admissions_committee.repository.FacultyTranslateRepository;
import com.epam.admissions_committee.repository.LanguageRepository;
import com.epam.admissions_committee.service.FacultyMappingService;
import com.epam.admissions_committee.service.mapper.FacultyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {
    @InjectMocks
    private FacultyServiceImpl facultyService;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private FacultyTranslateRepository facultyTranslateRepository;
    @Mock
    private LanguageRepository languageRepository;
    @Mock
    private FacultyMappingService facultyMappingService;

    @Test
    void createFacultyTest() {
        Language language = Language.builder().language("en").build();
        when(languageRepository.findByLanguage(any())).thenReturn(language);
        FacultyDto facultyDto = FacultyDto.builder().build();
        List<FacultyTranslate> facultyTranslateList = new ArrayList<>();
        facultyTranslateList.add(FacultyTranslate.builder().facultyName("abc").build());
        facultyDto.setFacultyTranslates(facultyTranslateList);
        facultyDto = facultyService.createFaculty(facultyDto, "en");
        verify(languageRepository, times(1)).findByLanguage(any());
        verify(facultyRepository, times(1)).save(any());
        assertEquals("en", facultyDto.getFacultyTranslates().get(0).getLanguage().getLanguage());
    }

    @Test
    void getFacultyTest() {
        Faculty faculty = Faculty.builder().build();
        List<FacultyTranslate> facultyTranslateList = new ArrayList<>();
        facultyTranslateList.add(FacultyTranslate.builder().facultyName("abc").build());
        faculty.setFacultyTranslates(facultyTranslateList);
        when(facultyTranslateRepository.findByName(any())).thenReturn(FacultyTranslate.builder().faculty(Faculty.builder().facultyId(1L).build()).build());
        when(facultyRepository.findFacultyByFacultyId(anyLong())).thenReturn(faculty);
        FacultyDto faculty1 = facultyService.getFaculty("abc");
        FacultyDto faculty2 = FacultyMapper.INSTANCE.mapFacultyDto(faculty);
        FacultyDto facultyDto = FacultyDto.builder().build();
        assertEquals(faculty1, faculty2);
    }

    @Test
    void listFacultyTest() {
        List<Faculty> facultyList = Collections.singletonList(Faculty.builder().build());
        when(facultyRepository.findAll()).thenReturn(facultyList);
        List<Faculty> facultyListResult = facultyService.listFaculty();
        assertEquals(facultyList, facultyListResult);
    }

    @Test
    void listPagedAndSortingFacultyByNameTest() {
        List<FacultyTranslate> facultyTranslateList = Collections.singletonList(FacultyTranslate.builder().build());
        when(languageRepository.findByLanguage(any())).thenReturn(Language.builder().build());
        when(facultyTranslateRepository.findAllByLanguage(any(), any())).thenReturn(facultyTranslateList);
        facultyService.listPagedAndSortingFacultyByName(1, 1, "en");
        verify(facultyTranslateRepository, times(1)).findAllByLanguage(any(), any());
    }

    @Test
    void listPagedAndSortingFacultyByStFoundPlTest() {
        when(facultyRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        facultyService.listPagedAndSortingFacultyByStFoundPl(1, 1);
        verify(facultyRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void listPagedAndSortingFacultyByTotPlTest() {
        when(facultyRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        facultyService.listPagedAndSortingFacultyByTotPl(1, 1);
        verify(facultyRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void updateFacultyTest() {
        Faculty faculty = Faculty.builder().build();
        FacultyDto facultyDto = FacultyMapper.INSTANCE.mapFacultyDto(faculty);
        when(facultyTranslateRepository.findByName(any())).thenReturn(FacultyTranslate.builder().faculty(Faculty.builder().facultyId(1L).build()).build());
        when(facultyRepository.findFacultyByFacultyId(anyLong())).thenReturn(faculty);
        when(facultyRepository.save(any())).thenReturn(faculty);
        when(facultyMappingService.populateFacultyWithPresentFacultyDtoFields(any(), any())).thenReturn(faculty);
        facultyService.updateFaculty("abc", facultyDto);
        verify(facultyRepository, times(1)).save(any());
    }

    @Test
    void deleteFacultyTest() {
        Faculty faculty = Faculty.builder().build();
        when(facultyTranslateRepository.findByName(any())).thenReturn(FacultyTranslate.builder().faculty(Faculty.builder().facultyId(1L).build()).build());
        when(facultyRepository.findFacultyByFacultyId(anyLong())).thenReturn(faculty);
        doNothing().when(facultyRepository).delete(any());
        facultyService.deleteFaculty("abc");
        verify(facultyRepository, times(1)).delete(any());
    }
}
