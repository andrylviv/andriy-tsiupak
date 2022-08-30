package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.model.User;
import com.epam.admissions_committee.repository.UserRepository;
import com.epam.admissions_committee.service.UserMappingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMappingService userMappingService;
    private final static String MOCK_EMAIL = "email@exem";

    @Test
    void getUserTest() {
        User expectedUser = User.builder().email(MOCK_EMAIL).build();
        when(userRepository.findByEmail(MOCK_EMAIL)).thenReturn(expectedUser);
        UserDto actualUser = userService.getUser(MOCK_EMAIL);
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    }

    @Test
    void listUsersTest() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(User.builder().build()));
        List<UserDto> userDtoList = userService.listUsers();
        assertThat(userDtoList, hasSize(1));
    }

    @Test
    void createUserTest() {
        User expectedUser = User.builder().email(MOCK_EMAIL).build();
        UserDto userDto = UserDto.builder().email(MOCK_EMAIL).build();
        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(expectedUser);
        UserDto user = userService.createUser(userDto);
        assertThat(user, equalTo(userDto));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void createUserWithExceptionTest() {
        UserDto userDto = UserDto.builder().email(MOCK_EMAIL).build();
        when(userRepository.existsByEmail(any())).thenReturn(true);
        assertThrows(RuntimeException.class, () -> userService.createUser(userDto));
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void updateUserTest() {
        User user = User.builder().email(MOCK_EMAIL).build();
        UserDto userDto = UserDto.builder().email(MOCK_EMAIL).build();
        when(userRepository.findByEmail(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        when(userMappingService.populateUserWithPresentUserDtoFields(any(), any())).thenReturn(user);
        String newEmail = "new_email@exem";
        userService.updateUser(newEmail, userDto);
        verify(userRepository, times(1)).findByEmail(any());
        verify(userMappingService, times(1)).populateUserWithPresentUserDtoFields(any(), any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void deleteUserTest() {
        User expectedUser = User.builder().email(MOCK_EMAIL).build();
        when(userRepository.findByEmail(MOCK_EMAIL)).thenReturn(expectedUser);
        doNothing().when(userRepository).delete(any());
        userService.deleteUser(MOCK_EMAIL);
        verify(userRepository, times(1)).delete(any());
    }
}
