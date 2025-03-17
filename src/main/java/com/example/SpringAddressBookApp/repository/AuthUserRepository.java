package com.example.SpringAddressBookApp.repository;

import com.example.SpringAddressBookApp.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    boolean existsByEmail(String email);
    AuthUser findByEmail(String email);
}
