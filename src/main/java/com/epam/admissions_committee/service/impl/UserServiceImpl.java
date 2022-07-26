package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.model.User;
import com.epam.admissions_committee.repository.UserRepository;
import com.epam.admissions_committee.service.UserMappingService;
import com.epam.admissions_committee.service.UserService;
import com.epam.admissions_committee.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private  final UserMappingService userMappingService;

    @Override
    public UserDto getUser(String email) {
        log.info("getUser by email {}", email);
        User user = userRepository.findByEmail(email);
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::mapUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("user already exists");
        }
        User user = UserMapper.INSTANCE.mapUser(userDto);
        user.setWrittenOn(Instant.now());
        user = userRepository.save(user);
        log.info("User with email {} created", userDto.getEmail());
        return UserMapper.INSTANCE.mapUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("updateUser with email {}", email);
        User persistedUser = userRepository.findByEmail(email);
        persistedUser = userMappingService.populateUserWithPresentUserDtoFields(persistedUser, userDto);
        User storedUser = userRepository.save(persistedUser);
        return UserMapper.INSTANCE.mapUserDto(storedUser);
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        log.info("deleteUser with email {}", email);
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        log.info("User with email {} successfully deleted", email);
    }
}
