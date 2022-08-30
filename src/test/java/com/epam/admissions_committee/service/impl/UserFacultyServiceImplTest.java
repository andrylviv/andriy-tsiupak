package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.model.*;
import com.epam.admissions_committee.repository.FacultyRepository;
import com.epam.admissions_committee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserFacultyServiceImplTest {
    @InjectMocks
    private UserFacultyServiceImpl userFacultyServiceImplService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FacultyRepository facultyRepository;

    @Test
    void regUserOnFacultyTest() {
        User user = User.builder().userInfo(UserInfo.builder().eieMath(7).eiePhysics(7).eieUkLang(7).build()).faculties(new HashSet<>()).build();
        when(userRepository.findByEmail(any())).thenReturn(user);
        when(facultyRepository.findFacultyByFacultyId(any())).thenReturn(Faculty.builder().build());
        when(userRepository.save(any())).thenReturn(user);
        userFacultyServiceImplService.regUserOnFaculty("test@com",1, 7, 7, 7);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void deleteUserTest() {
        String email = "test@com";
        User user = User.builder().faculties(new HashSet<>()).build();
        Faculty faculty = Faculty.builder().build();
        Set<Faculty> faculties = user.getFaculties();
        faculties.add(faculty);
        when(userRepository.findByEmail(email)).thenReturn(user);
        when(facultyRepository.findFacultyByFacultyId(any())).thenReturn(faculty);
        userFacultyServiceImplService.deleteUser(email, 1);
        verify(userRepository, times(1)).save(any());
    }
}
