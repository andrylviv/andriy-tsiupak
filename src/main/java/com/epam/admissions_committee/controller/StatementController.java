package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.service.StatementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/statement")
@RequiredArgsConstructor
@Log4j2
public class StatementController {
    StatementService statementService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{facultyId}")
    public void add(@PathVariable int facultyId) {
        log.info("added applicant by facultyId {}", facultyId);
         statementService.addApplicantToStatement(facultyId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/finalise/{facultyId}")
    public void finalise(@PathVariable int facultyId) {
        statementService.finaliseStatement(facultyId);
    }

    @DeleteMapping(value = "/{userEmail}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userEmail) {
        statementService.removeFromStatement(userEmail);
        return ResponseEntity.noContent().build();
    }
}
