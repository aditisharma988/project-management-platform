package com.project.authservice.repository;

import com.project.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndTenantId(String username, String tenantId);

    boolean existsByUsernameAndTenantId(String username, String tenantId);

}
