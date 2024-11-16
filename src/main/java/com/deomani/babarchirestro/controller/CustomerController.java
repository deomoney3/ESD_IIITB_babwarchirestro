package com.deomani.babarchirestro.controller;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.dto.CustomerResponse;
import com.deomani.babarchirestro.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }
}
