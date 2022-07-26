package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.service.StatementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Log4j2
public class StatementController {
    private final StatementService statementService;

    @ApiOperation("Add to statement")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/statement/{facultyId}")
    public void add(@PathVariable int facultyId) {
        log.info("added applicant by facultyId {}", facultyId);
         statementService.addApplicantToStatement(facultyId);
    }

    @ApiOperation("Finalise statement")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/statement/finalise/{facultyId}")
    public void finalise(@PathVariable int facultyId) {
        statementService.finaliseStatement(facultyId);
    }

    @ApiOperation("Delete user from statement")
    @DeleteMapping(value = "/statement/{userEmail}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userEmail) {
        statementService.removeFromStatement(userEmail);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Applicants on faculty")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/statement/applicants/faculty-id/{facultyId}")
    public List<UserDto> getApplicants(@PathVariable int facultyId) {
        return statementService.getFacultyApplicantList(facultyId);
    }

    @ApiOperation("Applicants on state founded places on faculty")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/statement/st-fond-applicants/faculty-id/{facultyId}")
    public List<UserDto> getStFondApplicants(@PathVariable int facultyId) {
        return statementService.getStFondApplicantList(facultyId);
    }

    @ApiOperation("Applicants on non state founded places on faculty")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/statement/non-st-fond-applicants/faculty-id/{facultyId}")
    public List<UserDto> getNonStFondApplicants(@PathVariable int facultyId) {
        return statementService.getNonStFondApplicantList(facultyId);
    }
}
