package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.controller.dto.FacultyDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.service.FacultyService;
import com.epam.admissions_committee.service.mapper.FacultyMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = FacultyController.class)
@AutoConfigureMockMvc
class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    FacultyService facultyService;
    FacultyDto facultyDto = FacultyDto
            .builder()
            .facultyId(1L)
            .stFundedPlaces(0)
            .totPlaces(2)
            .isEieMath(1)
            .isEieUkLang(1)
            .isEiePhysics(1)
            .build();
    Faculty faculty = FacultyMapper.INSTANCE.mapFaculty(facultyDto);

    @Test
    void createFacultyTest() throws Exception {
        when(facultyService.createFaculty(any(),any())).thenReturn(facultyDto);
        String body = (new ObjectMapper()).valueToTree(facultyDto).toString();
        mockMvc.perform(post("/api/v1/faculty/language/en")
                        .content(body).contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("facultyId").value(facultyDto.getFacultyId()));
    }

    @Test
    void getAllFacultiesTest() throws Exception {
        when(facultyService.listFaculty()).thenReturn(Collections.singletonList(faculty));
        mockMvc.perform(get("/api/v1/faculty"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$[0].stFundedPlaces").value(faculty.getStFundedPlaces()));
    }

    @Test
    void getAllFacultiesByNameTest() throws Exception {
        when(facultyService.listPagedAndSortingFacultyByName(1, 3, "ua")).thenReturn(Collections.singletonList(faculty));
        mockMvc.perform(get("/api/v1/faculty/pas-name/page/1/size/3/lang/ua"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$[0].stFundedPlaces").value(faculty.getStFundedPlaces()));
    }

    @Test
    void getAllFacultiesSFPlTest() throws Exception {
        Page<Faculty> page = new PageImpl<>(Collections.singletonList(faculty));
        when(facultyService.listPagedAndSortingFacultyByStFoundPl(1, 3)).thenReturn(page);
        mockMvc.perform(get("/api/v1/faculty/pas-s-f-p-l/page/1/size/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['content'][0].facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$['content'][0].stFundedPlaces").value(faculty.getStFundedPlaces()));
    }

    @Test
    void getAllFacultiesNSFPlTest() throws Exception {
        Page<Faculty> page = new PageImpl<>(Collections.singletonList(faculty));
        when(facultyService.listPagedAndSortingFacultyByTotPl(1, 3)).thenReturn(page);
        mockMvc.perform(get("/api/v1/faculty/pas-n-s-f-p-l/page/1/size/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$['content'][0].facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$['content'][0].stFundedPlaces").value(faculty.getStFundedPlaces()));
    }

    @Test
    void getFacultyTest() throws Exception {
        when(facultyService.getFaculty("abc")).thenReturn(facultyDto);
        mockMvc.perform(get("/api/v1/faculty/abc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("stFundedPlaces").value(faculty.getStFundedPlaces()));
    }

    @Test
    void updateFacultyTest() throws Exception {
        when(facultyService.updateFaculty("abc",facultyDto)).thenReturn(facultyDto);
        String body = (new ObjectMapper()).valueToTree(facultyDto).toString();
        mockMvc.perform(patch("/api/v1/faculty/abc")
                        .content(body).contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("stFundedPlaces").value(faculty.getStFundedPlaces()));
    }

    @Test
    void deleteFacultyTest() throws Exception {
        doNothing().when(facultyService).deleteFaculty(any());
        mockMvc.perform(delete("/api/v1/faculty/abc"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(facultyService, times(1)).deleteFaculty(any());
    }
}
