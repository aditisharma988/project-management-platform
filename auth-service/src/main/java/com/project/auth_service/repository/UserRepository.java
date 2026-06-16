package com.project.auth_service.repository;

import com.project.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Look up a user profile strictly inside their own company workspace
    Optional<User> findByUsernameAndTenantId(String username, String tenantId);

    // Safety check: verify no two users create identical accounts within the same workspace
    boolean existsByUsernameAndTenantId(String username, String tenantId);

}
