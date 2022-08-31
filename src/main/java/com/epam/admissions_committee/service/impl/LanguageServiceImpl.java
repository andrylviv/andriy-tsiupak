package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.model.Language;
import com.epam.admissions_committee.repository.LanguageRepository;
import com.epam.admissions_committee.service.LanguageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<Language> getAllLanguages() {
        log.info("get all languages");
        return languageRepository.findAll();
    }

    @Override
    public Language addLanguage(String lang) {
        Language language = new Language();
        language.setLanguage(lang);
        language = languageRepository.save(language);
        return language;
    }

    @Override
    public void removeLanguage(String lang) {
        log.info("deleteLanguage lang {}", lang);
        Language language = languageRepository.findByLanguage(lang);
        languageRepository.delete(language);
        log.info("Language lang {} successfully deleted", lang);
    }
}
