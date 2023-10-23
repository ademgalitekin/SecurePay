package com.firisbe.SecurePay.repository;

import com.firisbe.SecurePay.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findById(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}