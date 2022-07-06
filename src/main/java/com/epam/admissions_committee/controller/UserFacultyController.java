package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.service.UserFacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserFacultyController {
    private final UserFacultyService userFacultyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/userfaculty/userid/{userId}/facultyid/{facultyId}/eieuklang/{eieUkLang}/eiemath/{eieMath}/eiephysics/{eiePhysics}")
    public ResponseEntity<Void> regUser(@PathVariable("userId") int userId, @PathVariable("facultyId") int facultyId,
                                           @PathVariable("eieUkLang") int eieUkLang, @PathVariable("eieMath") int eieMath,
                                           @PathVariable("eiePhysics") int eiePhysics) {
        log.info("applicant userId {} registered on facultyId {}", userId, facultyId);
        userFacultyService.regUserOnFaculty(userId, facultyId, eieUkLang, eieMath, eiePhysics);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/userid/{userId}/facultyid/{facultyId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId, @PathVariable int facultyId) {
        userFacultyService.deleteUser(userId, facultyId);
        return ResponseEntity.noContent().build();
    }
}
