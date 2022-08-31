package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.model.Language;
import com.epam.admissions_committee.service.LanguageService;
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
@RequestMapping("/api/v1/language")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Log4j2
public class LanguageController {
    LanguageService languageService;

    @ApiOperation("Get language")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Language> getAllLanguages() {
        log.info("getAllLanguages");
        return languageService.getAllLanguages();
    }

    @ApiOperation("Add language")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{name}")
    public Language createLanguage(@PathVariable String name) {
        return languageService.addLanguage(name);
    }

    @ApiOperation("Delete language")
    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable String name) {
    languageService.removeLanguage(name);
        return ResponseEntity.noContent().build();
    }
}
