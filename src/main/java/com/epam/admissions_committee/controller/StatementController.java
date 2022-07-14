package com.epam.admissions_committee.controller;

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
    StatementService statementService;

    @ApiOperation("Add to statement")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/statement{facultyId}")
    public void add(@PathVariable int facultyId) {
        log.info("added applicant by facultyId {}", facultyId);
         statementService.addApplicantToStatement(facultyId);
    }

    @ApiOperation("Finalise statement")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/statement")
    public void finalise() {
        statementService.finaliseStatement();
    }

    @ApiOperation("Delete user from statement")
    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        statementService.removeFromStatement(userId);
        return ResponseEntity.noContent().build();
    }
}
