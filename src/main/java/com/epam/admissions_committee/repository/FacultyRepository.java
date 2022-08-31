package com.epam.admissions_committee.repository;

import com.epam.admissions_committee.model.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>, PagingAndSortingRepository<Faculty, Long> {
    Faculty findFacultyByFacultyId(Long id);
    Page<Faculty> findAll(Pageable pageable);
}
