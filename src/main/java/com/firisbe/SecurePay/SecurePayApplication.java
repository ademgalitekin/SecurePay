package com.firisbe.SecurePay;

import com.firisbe.SecurePay.model.ERole;
import com.firisbe.SecurePay.model.Role;
import com.firisbe.SecurePay.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurePayApplication.class, args);
	}

	@Bean
	CommandLineRunner initRoles(RoleService roleService) {
		return args -> {
			for (ERole roleName : ERole.values()) {
				if (roleService.findByName(roleName) == null) {
					Role role = new Role();
					role.setName(roleName);
					roleService.save(role);
				}
			}
		};
	}
}