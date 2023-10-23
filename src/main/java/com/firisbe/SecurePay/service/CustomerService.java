package com.firisbe.SecurePay.service;

import com.firisbe.SecurePay.model.Customer;
import com.firisbe.SecurePay.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findByEmail(String email) throws EntityNotFoundException {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Customer with email " + email + " not found"));
    }

    public Customer findById(Long id) throws EntityNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));
    }
}
