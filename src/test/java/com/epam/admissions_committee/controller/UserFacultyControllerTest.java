package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.service.UserFacultyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserFacultyController.class)
@AutoConfigureMockMvc
class UserFacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserFacultyService userFacultyService;

    @Test
    void regUserTest() throws Exception {
        doNothing().when(userFacultyService).regUserOnFaculty(any(), anyInt(), anyInt(), anyInt(), anyInt());
        mockMvc.perform(post("/api/v1/user-faculty/user-email/test@com/faculty-id/1/eie-uk-lang/8/eie-math/8/eie-physics/8"))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(userFacultyService, times(1)).regUserOnFaculty("test@com", 1, 8, 8, 8);
    }

    @Test
    void deleteUserTest() throws Exception {
        doNothing().when(userFacultyService).deleteUser(any(), anyInt());
        mockMvc.perform(delete("/api/v1/user-faculty/user-email/test@com/faculty-id/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(userFacultyService, times(1)).deleteUser("test@com", 1);
    }
}
