package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.WebConfig;
import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.service.StatementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = StatementController.class)
@AutoConfigureMockMvc
@Import(WebConfig.class)
class StatementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StatementService statementService;

    @Test
    void addTest() throws Exception {
        doNothing().when(statementService).addApplicantToStatement(anyInt());
        mockMvc.perform(post("/api/v1/statement/1"))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(statementService, times(1)).addApplicantToStatement(1);
    }

    @Test
    void finaliseTest() throws Exception {
        doNothing().when(statementService).finaliseStatement(anyInt());
        mockMvc.perform(post("/api/v1/statement/finalise/1"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(statementService, times(1)).finaliseStatement(1);
    }

    @Test
    void deleteUserTest() throws Exception {
        doNothing().when(statementService).removeFromStatement(any());
        mockMvc.perform(delete("/api/v1/statement/test@com"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(statementService, times(1)).removeFromStatement("test@com");
    }

    @Test
    void getApplicantsTest() throws Exception {
        UserDto userDto = UserDto
                .builder()
                .email("test@com")
                .isAdmin(0)
                .isBlocked(0)
                .password("00000000000")
                .userInfo(null)
                .build();

        when(statementService.getFacultyApplicantList(anyInt())).thenReturn(Collections.singletonList(userDto));
        mockMvc.perform(get("/api/v1/statement/applicants/faculty-id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value(userDto.getEmail()))
                .andExpect(jsonPath("$[0].password").value(userDto.getPassword()));
    }

    @Test
    void getStFondApplicantsTest() throws Exception {
        UserDto userDto = UserDto
                .builder()
                .email("test@com")
                .isAdmin(0)
                .isBlocked(0)
                .password("00000000000")
                .userInfo(null)
                .build();
        when(statementService.getStFondApplicantList(anyInt())).thenReturn(Collections.singletonList(userDto));
        mockMvc.perform(get("/api/v1/statement/st-fond-applicants/faculty-id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value(userDto.getEmail()))
                .andExpect(jsonPath("$[0].password").value(userDto.getPassword()));
    }

    @Test
    void getNonStFondApplicantsTest() throws Exception {
        UserDto userDto = UserDto
                .builder()
                .email("test@com")
                .isAdmin(0)
                .isBlocked(0)
                .password("00000000000")
                .userInfo(null)
                .build();
        when(statementService.getNonStFondApplicantList(anyInt())).thenReturn(Collections.singletonList(userDto));
        mockMvc.perform(get("/api/v1/statement/non-st-fond-applicants/faculty-id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value(userDto.getEmail()))
                .andExpect(jsonPath("$[0].password").value(userDto.getPassword()));
    }
}
