package com.epam.admissions_committee.controller;

import com.epam.admissions_committee.WebConfig;
import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
@Import(WebConfig.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    UserDto userDto = UserDto
            .builder()
            .email("test@com")
            .isAdmin(0)
            .isBlocked(0)
            .password("00000000000")
            .userInfo(null)
            .build();

    @Test
    void getAllUsersTest() throws Exception {
        when(userService.listUsers()).thenReturn(Collections.singletonList(userDto));
        mockMvc.perform(get("/api/v1/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].email").value(userDto.getEmail()));
    }

    @Test
    void getAllUsersTestExpectExceptionOnError() throws Exception {
        String message = "error message";
        when(userService.listUsers()).thenThrow(new NullPointerException(message));
        mockMvc.perform(get("/api/v1/user"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(message));
    }

    @Test
    void getUserTest() throws Exception {
        when(userService.getUser("test@com")).thenReturn(userDto);
        mockMvc.perform(get("/api/v1/user/test@com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("email").value(userDto.getEmail()));
    }

    @Test
    void createUserTest() throws Exception {
        when(userService.createUser(userDto)).thenReturn(userDto);
        String body = (new ObjectMapper()).valueToTree(userDto).toString();
        mockMvc.perform(post("/api/v1/user")
                .content(body).contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("email").value(userDto.getEmail()));
    }

    @Test
    void updateUserTest() throws Exception {
        when(userService.updateUser("test@com",userDto)).thenReturn(userDto);
        String body = (new ObjectMapper()).valueToTree(userDto).toString();
        mockMvc.perform(patch("/api/v1/user/test@com")
                        .content(body).contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("email").value(userDto.getEmail()));
    }

    @Test
    void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteUser(any());
        mockMvc.perform(delete("/api/v1/user/test@com"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(userService, times(1)).deleteUser(any());
    }
}
