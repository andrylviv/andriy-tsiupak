package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.model.Language;
import com.epam.admissions_committee.service.LanguageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = LanguageController.class)
@AutoConfigureMockMvc
class LanguageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    LanguageService languageService;
    Language language = Language
            .builder()
            .language("ua")
            .build();
    @Test
    void getAllLanguagesTest() throws Exception {
        when(languageService.getAllLanguages()).thenReturn(Collections.singletonList(language));
        mockMvc.perform(get("/api/v1/language"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].language").value(language.getLanguage()));
    }

    @Test
    void createUserTest() throws Exception {
        when(languageService.addLanguage("en")).thenReturn(language);
        String body = (new ObjectMapper()).valueToTree(language).toString();
        mockMvc.perform(post("/api/v1/language/en")
                        .content(body).contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("language").value(language.getLanguage()));
    }

    @Test
    void deleteLanguageTest() throws Exception {
        doNothing().when(languageService).removeLanguage(any());
        mockMvc.perform(delete("/api/v1/language/en"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(languageService, times(1)).removeLanguage("en");
    }
}
