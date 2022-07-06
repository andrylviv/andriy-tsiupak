package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.model.Language;
import com.epam.admissions_committee.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class LanguageController {
    LanguageService languageService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/language")
    public List<Language> getAllLanguages() {
        log.info("getAllLanguages");
        return languageService.getAllLanguages();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/language/{name}")
    public Language createLanguage(@PathVariable String name) {
        return languageService.addLanguage(name);
    }

    @DeleteMapping(value = "/language/{name}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable String name) {
    languageService.removeLanguage(name);
        return ResponseEntity.noContent().build();
    }
}
