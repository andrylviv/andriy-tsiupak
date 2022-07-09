package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.service.FacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class FacultyController {
    private final FacultyService facultyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/faculty")
    public FacultyDto createFaculty(@RequestBody FacultyDto facultyDto) {
        return facultyService.createFaculty(facultyDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/faculty")
    public List<Faculty> getAllFaculties() {
        return facultyService.listFaculty();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/faculty/{name}")
    public FacultyDto getUser(@PathVariable String name) {
        log.info("getFaculty by name {}", name);
        return facultyService.getFaculty(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/faculty/{name}")
    public FacultyDto updateFaculty(@PathVariable("name") String name, @RequestBody FacultyDto facultyDto) {
        return facultyService.updateFaculty(name, facultyDto);
    }

    @DeleteMapping(value = "/faculty/{name}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable String name) {
        facultyService.deleteFaculty(name);
        return ResponseEntity.noContent().build();
    }
}
