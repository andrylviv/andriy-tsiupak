package com.epam.admissions_committee.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String email;
    @NotBlank
    private String password;
    private int isAdmin;
    private int isBlocked;
    private UserInfoDto userInfo;
}
