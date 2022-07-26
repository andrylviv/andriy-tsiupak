package com.epam.admissions_committee.repository;

import com.epam.admissions_committee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
  boolean existsByEmail(String email);
}
