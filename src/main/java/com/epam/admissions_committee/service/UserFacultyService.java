package com.epam.admissions_committee.service;

public interface UserFacultyService {
    void regUserOnFaculty(String userEmail, int facultyId, int eieUkLang, int eieMath, int eiePhysics);
    void deleteUser(String userEmail, int facultyId);
}
