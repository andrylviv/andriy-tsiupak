package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.model.User;
import com.epam.admissions_committee.model.UserInfo;
import com.epam.admissions_committee.service.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMappingServiceImplTest {
    @InjectMocks
    UserMappingServiceImpl userMappingService;

    @Test
    void populateUserWithPresentUserDtoFieldsTest() {
        UserInfo userInfo = UserInfo.builder()
                .firstName("fn")
                .lastName("ln")
                .patronymic("ptnmc")
                .city("city")
                .region("reg")
                .school("11")
                .ukLang(1)
                .ukLiter(2)
                .eng(3)
                .algebra(4)
                .informatics(5)
                .geometry(6)
                .ukHistory(7)
                .phTraining(8)
                .physics(9)
                .eieUkLang(10)
                .eieMath(11)
                .eiePhysics(12)
                .build();
        User user = User.builder().email("email@exem").password("12345678").isAdmin(1).isBlocked(1).userInfo(userInfo).build();
        UserDto userDto = UserMapper.INSTANCE.mapUserDto(user);
        User actualUser = User.builder().build();
        userMappingService.populateUserWithPresentUserDtoFields(actualUser, userDto);
        assertEquals(user, actualUser);
    }
}
