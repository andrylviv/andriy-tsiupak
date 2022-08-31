package com.epam.admissions_committee.repository;

import com.epam.admissions_committee.model.FacultyTranslate;
import com.epam.admissions_committee.model.Language;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface FacultyTranslateRepository extends JpaRepository<FacultyTranslate, Long>, PagingAndSortingRepository<FacultyTranslate, Long> {
    @Query("select u from FacultyTranslate u where u.facultyName = ?1")
    FacultyTranslate findByName(String name);
    List<FacultyTranslate> findAllByLanguage(Language language, Pageable pageable);
}
