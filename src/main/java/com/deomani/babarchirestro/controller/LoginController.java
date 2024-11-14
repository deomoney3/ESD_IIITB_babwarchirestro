package com.deomani.babarchirestro.controller;

import com.deomani.babarchirestro.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deomani.babarchirestro.dto.LoginRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class LoginController {

    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody LoginRequest request) {
        boolean isAuthenticated = customerService.loginCustomerWithoutEncryption(request);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
