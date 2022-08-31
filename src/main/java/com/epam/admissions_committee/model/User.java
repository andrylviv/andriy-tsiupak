package com.epam.admissions_committee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private int isAdmin;
    private int isBlocked;
    private UserInfo userInfo;
    private Instant writtenOn;
    Set<Faculty> faculties;
}
