package com.epam.admissions_committee.service.mapper;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto mapUserDto(User user);
    User mapUser(UserDto userDto);
}
