package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.model.FacultyTranslate;
import com.epam.admissions_committee.model.Language;
import com.epam.admissions_committee.repository.FacultyRepository;
import com.epam.admissions_committee.repository.FacultyTranslateRepository;
import com.epam.admissions_committee.repository.LanguageRepository;
import com.epam.admissions_committee.service.FacultyMappingService;
import com.epam.admissions_committee.service.FacultyService;
import com.epam.admissions_committee.service.mapper.FacultyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final FacultyTranslateRepository facultyTranslateRepository;
    private final LanguageRepository languageRepository;
    private final FacultyMappingService facultyMappingService;

    @Override
    public FacultyDto createFaculty(FacultyDto facultyDto, String language) {
        log.info("createFaculty with name {}", facultyDto.getFacultyTranslates().get(0).getFacultyName());
        Faculty faculty = FacultyMapper.INSTANCE.mapFaculty(facultyDto);
        FacultyTranslate facultyTranslate = faculty.getFacultyTranslates().get(0);
        facultyTranslate.setFaculty(faculty);
        facultyTranslate.setLanguage(languageRepository.findByLanguage(language));
        faculty.setWrittenOn(Instant.now());
        facultyRepository.save(faculty);
        return FacultyMapper.INSTANCE.mapFacultyDto(faculty);
    }

    @Override
    public FacultyDto getFaculty(String name) {
        return FacultyMapper.INSTANCE.mapFacultyDto(getFacultyService(name));
    }

    private Faculty getFacultyService(String name) {
        FacultyTranslate facultyTranslate = facultyTranslateRepository.findByName(name);
        return facultyRepository.findFacultyByFacultyId(facultyTranslate.getFaculty().getFacultyId());
    }

    @Override
    public List<Faculty> listFaculty() {
        log.info("get all users");
        return facultyRepository.findAll();
    }

    @Override
    public List<Faculty> listPagedAndSortingFacultyByName(int page, int size, String lang) {
        Pageable sorted = PageRequest.of(page, size, Sort.by("facultyName"));
        Language language = languageRepository.findByLanguage(lang);
        return facultyTranslateRepository.findAllByLanguage(language, sorted).stream().map(FacultyTranslate::getFaculty).collect(Collectors.toList());
    }

    @Override
    public Page<Faculty> listPagedAndSortingFacultyByStFoundPl(int page, int size) {
        Pageable sorted = PageRequest.of(page, size, Sort.by("stFundedPlaces"));
        return facultyRepository.findAll(sorted);
    }

    @Override
    public Page<Faculty> listPagedAndSortingFacultyByTotPl(int page, int size) {
        Pageable sorted = PageRequest.of(page, size, Sort.by("totPlaces"));
        return facultyRepository.findAll(sorted);
    }

    @Override
    public FacultyDto updateFaculty(String name, FacultyDto facultyDto) {
        log.info("updateFaculty with email {}", name);
        Faculty persistedFaculty = getFacultyService(name);
        persistedFaculty = facultyMappingService.populateFacultyWithPresentFacultyDtoFields(persistedFaculty, facultyDto);
        Faculty storedFaculty = facultyRepository.save(persistedFaculty);
        return FacultyMapper.INSTANCE.mapFacultyDto(storedFaculty);
    }

    @Override
    public void deleteFaculty(String name) {
        log.info("deleteFaculty with email {}", name);
        Faculty faculty = getFacultyService(name);
        facultyRepository.delete(faculty);
        log.info("Faculty with name {} successfully deleted", name);
    }
}
