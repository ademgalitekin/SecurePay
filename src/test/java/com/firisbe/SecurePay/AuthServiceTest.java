package com.firisbe.SecurePay;

import com.firisbe.SecurePay.model.ERole;
import com.firisbe.SecurePay.model.Role;
import com.firisbe.SecurePay.payload.request.LoginRequest;
import com.firisbe.SecurePay.payload.request.SignupRequest;
import com.firisbe.SecurePay.payload.response.JwtResponse;
import com.firisbe.SecurePay.payload.response.MessageResponse;
import com.firisbe.SecurePay.repository.CustomerRepository;
import com.firisbe.SecurePay.repository.RoleRepository;
import com.firisbe.SecurePay.security.UserDetailsImpl;
import com.firisbe.SecurePay.security.jwt.JwtUtils;
import com.firisbe.SecurePay.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Test
    public void authenticateUserTest() {
        LoginRequest loginRequest = new LoginRequest("ademgalitekin", "password");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        when(jwtUtils.generateJwtToken(authentication)).thenReturn("eyJhbGciOiJIUzUxMiJ9");

        JwtResponse response = authService.authenticateUser(loginRequest);

        assertEquals("eyJhbGciOiJIUzUxMiJ9", response.getToken());
    }

    @Test
    public void registerUserTest() {
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setUsername("ademgalitekin");
        signUpRequest.setEmail("ademgalitekin760@gmail.com");
        signUpRequest.setPassword("password");

        when(customerRepository.existsByUsername(anyString())).thenReturn(false);
        when(customerRepository.existsByEmail(anyString())).thenReturn(false);

        Role role = new Role();
        role.setName(ERole.ROLE_ORDINARY_CUSTOMER);

        when(roleRepository.findByName(any())).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(anyString())).thenReturn("$2a$10$FMml0utQp8tszUpfVfxJj./xdH3iUp9c6cltTgTYHN6DqUyvPTA6W");

        MessageResponse response = authService.registerUser(signUpRequest);

        assertEquals("User registered successfully!", response.getMessage());
    }

    @Test
    public void registerUserWithExistingUsernameTest() {
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setUsername("existingUsername");
        signUpRequest.setEmail("email@example.com");
        signUpRequest.setPassword("password");

        when(customerRepository.existsByUsername(anyString())).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authService.registerUser(signUpRequest);
        });

        assertEquals("Error: Username is already taken!", exception.getMessage());
    }

    @Test
    public void registerUserWithExistingEmailTest() {
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setUsername("username");
        signUpRequest.setEmail("existingEmail@example.com");
        signUpRequest.setPassword("password");

        when(customerRepository.existsByUsername(anyString())).thenReturn(false);
        when(customerRepository.existsByEmail(anyString())).thenReturn(true);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authService.registerUser(signUpRequest);
        });

        assertEquals("Error: Email is already in use!", exception.getMessage());
    }

    @Test
    public void registerUserWithInvalidRoleTest() {
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setUsername("ademgalitekin");
        signUpRequest.setEmail("ademgalitekin760@gmail.com");
        signUpRequest.setPassword("password");
        signUpRequest.setRole(Collections.singleton("invalidRole"));

        when(customerRepository.existsByUsername(anyString())).thenReturn(false);
        when(customerRepository.existsByEmail(anyString())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authService.registerUser(signUpRequest);
        });

        assertEquals("Error: Role is not found.", exception.getMessage());
    }
}
