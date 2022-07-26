package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.controller.dto.UserDto;
import com.epam.admissions_committee.model.*;
import com.epam.admissions_committee.repository.*;
import com.epam.admissions_committee.service.StatementService;
import com.epam.admissions_committee.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class StatementServiceImpl implements StatementService {
    private final StatementRepository statementRepository;
    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public void addApplicantToStatement(int facultyId) {
        Faculty faculty = facultyRepository.findFacultyByFacultyId((long)facultyId);
        Set<User> users = faculty.getUsers();
        users.stream().filter(user -> !statementRepository.existsByUser(user)).forEach(user -> statementRepository.save(Statement.builder().user(user).faculty(faculty).build()));
    }

    @Override
    public void removeFromStatement(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        Statement statement = statementRepository.findByUser(user);
        statementRepository.delete(statement);
    }

    @Override
    public void finaliseStatement(int facultyId) {
        Faculty faculty = facultyRepository.findFacultyByFacultyId((long)facultyId);
        List<Statement> statementList = faculty.getStatements();
        List<Applicant> applicantList = new ArrayList<>();
            for (Statement st:statementList) {
                UserInfo userInfo = st.getUser().getUserInfo();
                Applicant applicant = new Applicant();
                applicant.setApplicantId(userInfo.getUserId());
                applicant.setMark(getAverageMark(userInfo, faculty));
                applicantList.add(applicant);
            }
            finalise(applicantList, faculty);
    }

    private static int getAverageMark(UserInfo userInfo, Faculty faculty) {
        int avrCertMark = (userInfo.getUkLang() + userInfo.getUkLiter() + userInfo.getEng() + userInfo.getAlgebra() + userInfo.getInformatics()+
                userInfo.getGeometry() + userInfo.getUkHistory() + userInfo.getPhTraining() + userInfo.getPhysics())/9;
        int eieMark = 0;
        int divisor = 0;
        if (faculty.getIsEieUkLang()==1) {
            eieMark += userInfo.getEieUkLang();
            ++divisor;
        }
        if (faculty.getIsEieMath()==1) {
            eieMark += userInfo.getEieMath();
            ++divisor;
        }
        if (faculty.getIsEiePhysics()==1) {
            eieMark += userInfo.getEiePhysics();
            ++divisor;
        }
        int avrEieMark = eieMark / divisor;
    return  (avrCertMark + avrEieMark) / 2;
    }

    private void finalise(List<Applicant> applicantList, Faculty faculty) {
        applicantList.sort((a, b) -> ((Integer)b.getMark()).compareTo(a.getMark()));
        if (applicantList.size() >= faculty.getTotPlaces()) {
            List<Applicant> applicantListStFoun = applicantList.subList(0, faculty.getStFundedPlaces());
            List<Applicant> applicantListNonStFoun = applicantList.subList(faculty.getStFundedPlaces(), faculty.getTotPlaces());
            setStFonPlFlag(applicantListStFoun);
            setNonStFonPlFlag(applicantListNonStFoun);
        }
        if (applicantList.size() <= faculty.getTotPlaces()) {
            if (applicantList.size() >= faculty.getStFundedPlaces()) {
                List<Applicant> applicantListStFoun = applicantList.subList(0, faculty.getStFundedPlaces());
                setStFonPlFlag(applicantListStFoun);
            }
            if (applicantList.size() <= faculty.getStFundedPlaces()) {
                List<Applicant> applicantListStFoun = applicantList;
                setStFonPlFlag(applicantListStFoun);
            }
            if (applicantList.size() >= faculty.getStFundedPlaces()) {
                List<Applicant> applicantListNonStFoun = applicantList.subList(faculty.getStFundedPlaces(), applicantList.size());
                setNonStFonPlFlag(applicantListNonStFoun);
            }
        }
    }

    private void setStFonPlFlag(List<Applicant> applicantListStFoun) {
        for (Applicant applicant : applicantListStFoun) {
            Statement statement = statementRepository.findByUser(applicant.getApplicantId());
            statement.setStFonPl(1);
            statement.setNonStFonPl(0);
            statementRepository.save(statement);
        }
    }

    private void setNonStFonPlFlag(List<Applicant> applicantListNonStFoun) {
        for (Applicant applicant : applicantListNonStFoun) {
            Statement statement = statementRepository.findByUser(applicant.getApplicantId());
            statement.setStFonPl(0);
            statement.setNonStFonPl(1);
            statementRepository.save(statement);
        }
    }

    public List<UserDto> getFacultyApplicantList(int facultyId) {
        Faculty faculty = facultyRepository.findFacultyByFacultyId((long)facultyId);
        List<Statement> applicantList = statementRepository.findByFaculty(faculty);
        return applicantList.stream().map(statement -> UserMapper.INSTANCE.mapUserDto(statement.getUser())).collect(Collectors.toList());
    }

    public  List<UserDto> getStFondApplicantList(int facultyId) {
        Faculty faculty = facultyRepository.findFacultyByFacultyId((long)facultyId);
        List<Statement> applicantList = statementRepository.findStFonPlUserByFaculty(faculty);
        return applicantList.stream().map(statement -> UserMapper.INSTANCE.mapUserDto(statement.getUser())).collect(Collectors.toList());
    }

    public  List<UserDto> getNonStFondApplicantList(int facultyId) {
        Faculty faculty = facultyRepository.findFacultyByFacultyId((long)facultyId);
        List<Statement> applicantList = statementRepository.findNonStFonPlUserByFaculty(faculty);
        return applicantList.stream().map(statement -> UserMapper.INSTANCE.mapUserDto(statement.getUser())).collect(Collectors.toList());
    }
}
