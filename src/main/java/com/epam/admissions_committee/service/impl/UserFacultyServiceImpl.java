package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.model.User;
import com.epam.admissions_committee.repository.FacultyRepository;
import com.epam.admissions_committee.repository.UserRepository;
import com.epam.admissions_committee.service.UserFacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.Set;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserFacultyServiceImpl implements UserFacultyService {
    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;

    public void regUserOnFaculty(String userEmail, int facultyId, int eieUkLang, int eieMath, int eiePhysics) {
        User user = userRepository.findByEmail(userEmail);
        user.getUserInfo().setEieUkLang(eieUkLang);
        user.getUserInfo().setEieMath(eieMath);
        user.getUserInfo().setEiePhysics(eiePhysics);
        Faculty faculty = facultyRepository.findFacultyByFacultyId((long) facultyId);
        Set<Faculty> faculties = user.getFaculties();
        faculties.add(faculty);
        userRepository.save(user);
    }

    public void deleteUser(String userEmail, int facultyId) {
        User user = userRepository.findByEmail(userEmail);
        Faculty faculty = facultyRepository.findFacultyByFacultyId((long)facultyId);
        Set<Faculty> faculties = user.getFaculties();
        faculties.remove(faculty);
        user.setFaculties(faculties);
        userRepository.save(user);
    }
}
