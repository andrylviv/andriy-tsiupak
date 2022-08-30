package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.model.Statement;
import com.epam.admissions_committee.model.User;
import com.epam.admissions_committee.repository.FacultyRepository;
import com.epam.admissions_committee.repository.StatementRepository;
import com.epam.admissions_committee.repository.UserRepository;
import com.epam.admissions_committee.service.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatementServiceImplTest {
    @InjectMocks
    private StatementServiceImpl statementService;
    @Mock
    private StatementRepository statementRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FacultyRepository facultyRepository;

    @Test
    void addApplicantToStatementTest() {
        Set<User> users = new HashSet<>();
        users.add(new User());
        when(facultyRepository.findFacultyByFacultyId(anyLong())).thenReturn(Faculty.builder().users(users).build());
        when(statementRepository.existsByUser(any())).thenReturn(false);
        when(statementRepository.save(any())).thenReturn(Statement.builder().build());
        statementService.addApplicantToStatement(1);
        verify(statementRepository, times(1)).save(any());
    }

    @Test
    void removeFromStatementTest() {
        when(userRepository.findByEmail(any())).thenReturn(User.builder().build());
        when(statementRepository.findByUser(any())).thenReturn(Statement.builder().build());
        doNothing().when(statementRepository).delete(any());
        statementService.removeFromStatement("test@com");
        verify(statementRepository, times(1)).delete(any());
    }

    @Test
    void getFacultyApplicantListTest() {
        List<Statement> statementList = new ArrayList<>();
        statementList.add(new Statement());
        when(facultyRepository.findFacultyByFacultyId(anyLong())).thenReturn(Faculty.builder().build());
        when(statementRepository.findByFaculty(any())).thenReturn(statementList);
        List<UserDto> userDtoList= statementService.getFacultyApplicantList(1);
        assertEquals(UserMapper.INSTANCE.mapUserDto(statementList.get(0).getUser()), userDtoList.get(0));
    }

    @Test
    void getStFondApplicantListTest() {
        List<Statement> statementList = new ArrayList<>();
        statementList.add(new Statement());
        when(facultyRepository.findFacultyByFacultyId(anyLong())).thenReturn(Faculty.builder().build());
        when(statementRepository.findStFonPlUserByFaculty(any())).thenReturn(statementList);
        List<UserDto> userDtoList= statementService.getStFondApplicantList(1);
        assertEquals(UserMapper.INSTANCE.mapUserDto(statementList.get(0).getUser()), userDtoList.get(0));
    }

    @Test
    void getNonStFondApplicantListTest() {
        List<Statement> statementList = new ArrayList<>();
        statementList.add(new Statement());
        when(facultyRepository.findFacultyByFacultyId(anyLong())).thenReturn(Faculty.builder().build());
        when(statementRepository.findNonStFonPlUserByFaculty(any())).thenReturn(statementList);
        List<UserDto> userDtoList= statementService.getNonStFondApplicantList(1);
        assertEquals(UserMapper.INSTANCE.mapUserDto(statementList.get(0).getUser()), userDtoList.get(0));
    }
}
