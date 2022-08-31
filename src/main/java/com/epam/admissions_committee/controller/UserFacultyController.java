package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.service.UserFacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user-faculty/user-email")
@RequiredArgsConstructor
@Log4j2
public class UserFacultyController {
    private final UserFacultyService userFacultyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{userEmail}/faculty-id/{facultyId}/eie-uk-lang/{eieUkLang}/eie-math/{eieMath}/eie-physics/{eiePhysics}")
    public void regUser(@PathVariable("userEmail") String userEmail, @PathVariable("facultyId") int facultyId,
                        @PathVariable("eieUkLang") int eieUkLang, @PathVariable("eieMath") int eieMath,
                        @PathVariable("eiePhysics") int eiePhysics) {
        log.info("applicant userId {} registered on facultyId {}", userEmail, facultyId);
        userFacultyService.regUserOnFaculty(userEmail, facultyId, eieUkLang, eieMath, eiePhysics);
    }

    @DeleteMapping(value = "/{userEmail}/faculty-id/{facultyId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userEmail") String userEmail, @PathVariable("facultyId") int facultyId) {
        userFacultyService.deleteUser(userEmail, facultyId);
        return ResponseEntity.noContent().build();
    }
}
