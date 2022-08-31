package com.epam.admissions_committee.repository;

import com.epam.admissions_committee.model.Faculty;
import com.epam.admissions_committee.model.Statement;
import com.epam.admissions_committee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StatementRepository extends JpaRepository<Statement, Long> {
    boolean existsByUser(User user);
    Statement findByUser(User user);
    List<Statement> findByFaculty(Faculty faculty);
    @Query("select s from Statement s where s.stFonPl = 1 and s.faculty = ?1")
    List<Statement> findStFonPlUserByFaculty(Faculty faculty);
    @Query("select s from Statement s where s.nonStFonPl = 1 and s.faculty = ?1")
    List<Statement> findNonStFonPlUserByFaculty(Faculty faculty);
}
