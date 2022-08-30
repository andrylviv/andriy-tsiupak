package com.epam.admissions_committee.service.impl;

import com.epam.admissions_committee.model.*;
import com.epam.admissions_committee.repository.FacultyRepository;
import com.epam.admissions_committee.repository.StatementRepository;
import com.epam.admissions_committee.repository.UserRepository;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.epam.admissions_committee.service.impl.StatementServiceImpl")
class StatementServiceImplPrivateTest {
    public StatementServiceImplPrivateTest() {
    }

    @InjectMocks
    private StatementServiceImpl statementService;
    @Mock
    private StatementRepository statementRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FacultyRepository facultyRepository;

    @Test
    public void finaliseStatementTest() throws Exception {
        List<Statement> statementList = new ArrayList<>();
        User user = User.builder().userInfo(UserInfo.builder().build()).build();
        Statement statement = Statement.builder().user(user).build();
        statementList.add(statement);
        StatementServiceImpl statementService1 = PowerMockito.spy(statementService);
        Mockito.when(facultyRepository.findFacultyByFacultyId(anyLong())).thenReturn(Faculty.builder().statements(statementList).build());
        PowerMockito.doReturn(1d).when(statementService1, "getAverageMark", any(), any());
        PowerMockito.doNothing().when(statementService1, "finalise", any(), any());
        statementService1.finaliseStatement(1);
        verifyPrivate(statementService1).invoke("finalise", any(), any());
    }

    @Test
    public final void getAverageMarkTest() throws Exception {
        UserInfo userInfo = UserInfo.builder()
                .ukLang(1)
                .ukLiter(2)
                .eng(3)
                .algebra(4)
                .informatics(5)
                .geometry(6)
                .ukHistory(7)
                .phTraining(8)
                .physics(9)
                .eieUkLang(10)
                .eieMath(11)
                .eiePhysics(12)
                .build();
        Faculty faculty = Faculty.builder().isEieMath(1).isEieUkLang(1).isEiePhysics(1).build();
        double expected = 6.5;
        double result = Whitebox.invokeMethod(new StatementServiceImpl(statementRepository,userRepository,facultyRepository), "getAverageMark", userInfo, faculty);
        Assert.assertEquals(expected, result, 0.0);
    }

    @Test
    public final void finaliseTest() throws Exception {
        Faculty faculty = Faculty.builder().stFundedPlaces(1).totPlaces(2).build();
        List<Applicant> applicantList = new ArrayList<>();
        Applicant applicant1 = new Applicant();
        applicant1.setApplicantId(User.builder().id(1L).build());
        applicant1.setMark(5);
        Applicant applicant2 = new Applicant();
        applicant2.setApplicantId(User.builder().id(2L).build());
        applicant2.setMark(6);
        Applicant applicant3 = new Applicant();
        applicant3.setApplicantId(User.builder().id(3L).build());
        applicant3.setMark(7);
        applicantList.add(applicant1);
        applicantList.add(applicant2);
        applicantList.add(applicant3);
        StatementServiceImpl statementService1 = PowerMockito.spy(statementService);
        PowerMockito.doNothing().when(statementService1, "setStFonPlFlag", any());
        PowerMockito.doNothing().when(statementService1, "setNonStFonPlFlag", any());
        Whitebox.invokeMethod(statementService1, "finalise", applicantList, faculty);
        verifyPrivate(statementService1).invoke("setStFonPlFlag", any());
        verifyPrivate(statementService1).invoke("setNonStFonPlFlag", any());
    }

    @Test
    public final void setStFonPlFlagTest() throws Exception {
        Statement statement = Statement.builder().build();
        List<Applicant> applicantList = new ArrayList<>();
        Applicant applicant = new Applicant();
        applicant.setApplicantId(User.builder().id(1L).build());
        applicant.setMark(5);
        applicantList.add(applicant);
        Mockito.when(statementRepository.findByUser(any())).thenReturn(statement);
        Mockito.when(statementRepository.save(any())).thenReturn(statement);
        StatementServiceImpl statementService1 = PowerMockito.spy(statementService);
        Whitebox.invokeMethod(statementService1, "setStFonPlFlag", applicantList);
        Mockito.verify(statementRepository, times(1)).save(any());
    }

    @Test
    public final void setNonStFonPlFlagTest() throws Exception {
        Statement statement = Statement.builder().build();
        List<Applicant> applicantList = new ArrayList<>();
        Applicant applicant = new Applicant();
        applicant.setApplicantId(User.builder().id(1L).build());
        applicant.setMark(5);
        applicantList.add(applicant);
        Mockito.when(statementRepository.findByUser(any())).thenReturn(statement);
        Mockito.when(statementRepository.save(any())).thenReturn(statement);
        StatementServiceImpl statementService1 = PowerMockito.spy(statementService);
        Whitebox.invokeMethod(statementService1, "setNonStFonPlFlag", applicantList);
        Mockito.verify(statementRepository, times(1)).save(any());
    }
}
