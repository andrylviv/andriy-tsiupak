package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.controller.dto.UserInfoDto;
import com.epam.admissions_committee.model.User;
import com.epam.admissions_committee.model.UserInfo;
import com.epam.admissions_committee.service.UserMappingService;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserMappingServiceImpl implements UserMappingService {
    @Override
    public User populateUserWithPresentUserDtoFields(User user, UserDto userDto) {
        String email = userDto.getEmail();
        if(Objects.nonNull(email)) {
            user.setEmail(email);
        }
        String password = userDto.getPassword();
        if(Objects.nonNull(password)) {
            user.setPassword(password);
        }
        user.setIsAdmin(userDto.getIsAdmin());
        user.setIsBlocked(userDto.getIsBlocked());
        UserInfoDto userInfoDto = userDto.getUserInfo();
        if(Objects.nonNull(userInfoDto)) {
            mapUserInfo(user, userInfoDto);
        }
        return user;
    }
    void mapUserInfo(User user, UserInfoDto userInfoDto) {
        UserInfo userInfo = user.getUserInfo();
        if(!Objects.nonNull(userInfo)) {
            userInfo = new UserInfo();
            user.setUserInfo(userInfo);
        }
        userInfo.setFirstName(userInfoDto.getFirstName());
        userInfo.setLastName(userInfoDto.getLastName());
        userInfo.setPatronymic(userInfoDto.getPatronymic());
        userInfo.setCity(userInfoDto.getCity());
        userInfo.setRegion(userInfoDto.getRegion());
        userInfo.setSchool(userInfoDto.getSchool());
        userInfo.setUkLang(userInfoDto.getUkLang());
        userInfo.setUkLiter(userInfoDto.getUkLiter());
        userInfo.setEng(userInfoDto.getEng());
        userInfo.setAlgebra(userInfoDto.getAlgebra());
        userInfo.setInformatics(userInfoDto.getInformatics());
        userInfo.setGeometry(userInfoDto.getGeometry());
        userInfo.setUkHistory(userInfoDto.getUkHistory());
        userInfo.setPhTraining(userInfoDto.getPhTraining());
        userInfo.setPhysics(userInfoDto.getPhysics());
        userInfo.setEieUkLang(userInfoDto.getEieUkLang());
        userInfo.setEieMath(userInfoDto.getEieMath());
        userInfo.setEiePhysics(userInfoDto.getEiePhysics());
    }
}
