package com.epam.admissions_committee.service;

import com.epam.admissions_committee.model.Language;
import java.util.List;

public interface LanguageService {
    List<Language> getAllLanguages();
    Language addLanguage(String lang);
    void removeLanguage(String lang);
}
