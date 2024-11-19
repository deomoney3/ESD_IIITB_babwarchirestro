package com.deomani.babarchirestro.controller;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.dto.CustomerResponse;
import com.deomani.babarchirestro.entity.Customer;
import com.deomani.babarchirestro.helper.JWTHelper;
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
    private final JWTHelper jwtHelper;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String token, @RequestBody CustomerRequest request) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String email = jwtHelper.extractUsername(token);
        validateTokenForEmail(token,email);
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @GetMapping("/fetch/{email}")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(@RequestHeader("Authorization") String token, @PathVariable String email) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        validateTokenForEmail(token, email);
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }


    private void validateTokenForEmail(String token, String email) {
        String extractedEmail = jwtHelper.extractUsername(token);
        if (!extractedEmail.equals(email)) {
            throw new RuntimeException("Unauthorized: Token does not match provided email");
        }
    }

}
