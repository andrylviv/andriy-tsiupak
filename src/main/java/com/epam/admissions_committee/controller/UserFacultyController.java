package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.service.UserFacultyService;
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
@RequestMapping("/api/v1/user-faculty/user-email")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Log4j2
public class UserFacultyController {
    private final UserFacultyService userFacultyService;

    @ApiOperation("Register applicant")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{userEmail}/faculty-id/{facultyId}/eie-uk-lang/{eieUkLang}/eie-math/{eieMath}/eie-physics/{eiePhysics}")
    public void regUser(@PathVariable("userEmail") String userEmail, @PathVariable("facultyId") int facultyId,
                        @PathVariable("eieUkLang") int eieUkLang, @PathVariable("eieMath") int eieMath,
                        @PathVariable("eiePhysics") int eiePhysics) {
        log.info("applicant userId {} registered on facultyId {}", userEmail, facultyId);
        userFacultyService.regUserOnFaculty(userEmail, facultyId, eieUkLang, eieMath, eiePhysics);
    }

    @ApiOperation("Remove applicant")
    @DeleteMapping(value = "/{userEmail}/faculty-id/{facultyId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userEmail") String userEmail, @PathVariable("facultyId") int facultyId) {
        userFacultyService.deleteUser(userEmail, facultyId);
        return ResponseEntity.noContent().build();
    }
}
