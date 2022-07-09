package com.epam.admissions_committee.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    @Email
    private String email;
    @NotBlank
    private String password;
    @Positive
    private int isAdmin;
    @Positive
    private int isBlocked;
    private UserInfoDto userInfo;
}
