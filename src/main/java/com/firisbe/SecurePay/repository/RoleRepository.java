package com.firisbe.SecurePay.repository;

import com.firisbe.SecurePay.model.ERole;
import com.firisbe.SecurePay.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
