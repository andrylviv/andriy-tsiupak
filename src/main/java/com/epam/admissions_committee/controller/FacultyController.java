package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.service.FacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/faculty")
@RequiredArgsConstructor
@Log4j2
public class FacultyController {
    private final FacultyService facultyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/language/{lang}")
    public FacultyDto createFaculty(@PathVariable("lang") String lang, @RequestBody FacultyDto facultyDto) {
        return facultyService.createFaculty(facultyDto, lang);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.listFaculty();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{name}")
    public FacultyDto getUser(@PathVariable String name) {
        log.info("getFaculty by name {}", name);
        return facultyService.getFaculty(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{name}")
    public FacultyDto updateFaculty(@PathVariable("name") String name, @RequestBody FacultyDto facultyDto) {
        return facultyService.updateFaculty(name, facultyDto);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String name) {
        facultyService.deleteFaculty(name);
        return ResponseEntity.noContent().build();
    }
}
