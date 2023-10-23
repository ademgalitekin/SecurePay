package com.firisbe.SecurePay.service;

import com.firisbe.SecurePay.model.ERole;
import com.firisbe.SecurePay.model.Role;
import com.firisbe.SecurePay.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByName(ERole name) {
        return roleRepository.findByName(name).orElse(null);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
