package com.epam.admissions_committee.repository;

import com.epam.admissions_committee.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findByLanguage(String language);
}
