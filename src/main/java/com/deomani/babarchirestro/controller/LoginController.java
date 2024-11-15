package com.deomani.babarchirestro.controller;

import com.deomani.babarchirestro.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deomani.babarchirestro.dto.LoginRequest;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class LoginController {

    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        String token = customerService.login(request);

        if ("Wrong Password or Email".equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        return ResponseEntity.ok(Map.of("token", token));
    }
}
