package com.melikash98.AdminSpring.Repository;

import com.melikash98.AdminSpring.Model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
    Optional<AdminUser> findByEmail(String email);
    Optional<AdminUser> findById(String id);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
