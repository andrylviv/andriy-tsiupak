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
@RequestMapping("/api/v1")
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
    @PostMapping(value = "/userfaculty/userid/{userId}/facultyid/{facultyId}/eieuklang/{eieUkLang}/eiemath/{eieMath}/eiephysics/{eiePhysics}")
    public ResponseEntity<Void> regUser(@PathVariable("userId") int userId, @PathVariable("facultyId") int facultyId,
                                        @PathVariable("eieUkLang") int eieUkLang, @PathVariable("eieMath") int eieMath,
                                        @PathVariable("eiePhysics") int eiePhysics) {
        log.info("applicant userId {} registered on facultyId {}", userId, facultyId);
        userFacultyService.regUserOnFaculty(userId, facultyId, eieUkLang, eieMath, eiePhysics);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Remove applicant")
    @DeleteMapping(value = "/userfaculty/userid/{userId}/facultyid/{facultyId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId, @PathVariable int facultyId) {
        userFacultyService.deleteUser(userId, facultyId);
        return ResponseEntity.noContent().build();
    }
}
