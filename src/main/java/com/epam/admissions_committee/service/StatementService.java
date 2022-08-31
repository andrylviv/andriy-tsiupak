package com.epam.admissions_committee.service;

import com.epam.admissions_committee.controller.dto.UserDto;
import java.util.List;

public interface StatementService {
    void addApplicantToStatement(int facultyId);
    void removeFromStatement(String userEmail);
    void finaliseStatement(int facultyId);
    List<UserDto> getFacultyApplicantList(int facultyId);
    List<UserDto> getStFondApplicantList(int facultyId);
    List<UserDto> getNonStFondApplicantList(int facultyId);
}
