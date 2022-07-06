package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.service.StatementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class StatementController {
    StatementService statementService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/statement{facultyId}")
    public void add(@PathVariable int facultyId) {
        log.info("added applicant by facultyId {}", facultyId);
         statementService.addApplicantToStatement(facultyId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/statement")
    public void finalise() {
        statementService.finaliseStatement();
    }

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        statementService.removeFromStatement(userId);
        return ResponseEntity.noContent().build();
    }
}
