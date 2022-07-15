package com.epam.admissions_committee.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import com.epam.admissions_committee.controller.validation.ValidPass;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    @Email
    private String email;
    @NotBlank
    @ValidPass
    private String password;
    @PositiveOrZero
    private int isAdmin;
    @PositiveOrZero
    private int isBlocked;
    private UserInfoDto userInfo;
}
