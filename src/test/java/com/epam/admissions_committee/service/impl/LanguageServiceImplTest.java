package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.model.Language;
import com.epam.admissions_committee.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class LanguageServiceImplTest {
    @InjectMocks
    private LanguageServiceImpl languageService;
    @Mock
    private LanguageRepository languageRepository;
    @Test

    void getAllLanguagesTest() {
        List<Language> languageList = new ArrayList<>();
        when(languageRepository.findAll()).thenReturn(languageList);
        List<Language> actualLanguageList = languageService.getAllLanguages();
        assertEquals(actualLanguageList, languageList);
    }

    @Test
    void addLanguageTest() {
        String language = "en";
        when(languageRepository.save(any())).thenReturn(Language.builder().language(language).build());
        Language actualLanguage = languageService.addLanguage(language);
        assertEquals(language, actualLanguage.getLanguage());
    }

    @Test
    void removeLanguageTest() {
        String language = "en";
        when(languageRepository.findByLanguage(any())).thenReturn(Language.builder().language(language).build());
        doNothing().when(languageRepository).delete(any());
        languageService.removeLanguage(language);
        verify(languageRepository, times(1)).delete(any());
    }
}
