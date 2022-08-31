package com.epam.admissions_committee.service;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.model.User;

public interface UserMappingService {
    User populateUserWithPresentUserDtoFields(User user, UserDto userDto);
}
