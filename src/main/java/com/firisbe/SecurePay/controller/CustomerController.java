package com.firisbe.SecurePay.controller;

import com.firisbe.SecurePay.payload.request.LoginRequest;
import com.firisbe.SecurePay.payload.request.SignupRequest;
import com.firisbe.SecurePay.payload.response.JwtResponse;
import com.firisbe.SecurePay.payload.response.MessageResponse;
import com.firisbe.SecurePay.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.firisbe.SecurePay.constant.RestPathConstants.*;

@RestController
@RequestMapping(path = ROOT_CUSTOMER_PATH)
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final AuthService authService;

    @PostMapping(SIGN_IN_PATH)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
    }

    @PostMapping(SIGN_UP_PATH)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            MessageResponse response = authService.registerUser(signUpRequest);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
    }
}