package com.deomani.babarchirestro.service;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.dto.CustomerResponse;
import com.deomani.babarchirestro.entity.Customer;
import com.deomani.babarchirestro.mapper.CustomerMapper;
import com.deomani.babarchirestro.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.deomani.babarchirestro.dto.LoginRequest;
import com.deomani.babarchirestro.helper.JWTHelper;
import static java.lang.String.format;
import com.deomani.babarchirestro.helper.EncryptionService;

import java.util.Optional;

@Service
@RequiredArgsConstructor


public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        repo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }


    public String login(LoginRequest request) {
        // Use Optional<Customer> to correctly handle potential null values
        Customer customer = getCustomer(request.email());
        if(!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }

        return jwtHelper.generateToken(request.email());
    }


    public String updateCustomer(CustomerRequest request) {
        Customer existingCustomer = repo.findByEmail(request.email()).orElseThrow(() ->
                new RuntimeException("Customer not found with email: " + request.email())
        );

        existingCustomer.setFirstName(request.firstName() != null ? request.firstName() : existingCustomer.getFirstName());
        existingCustomer.setLastName(request.lastName() != null ? request.lastName() : existingCustomer.getLastName());
        existingCustomer.setPassword(request.password() != null ? request.password() : existingCustomer.getPassword());
        existingCustomer.setAddress(request.address() != null ? request.address() : existingCustomer.getAddress());
        existingCustomer.setCity(request.city() != null ? request.city() : existingCustomer.getCity());
        existingCustomer.setPincode(request.pincode() != null ? request.pincode() : existingCustomer.getPincode());
        if (request.password() != null) {
            existingCustomer.setPassword(passwordEncoder.encode(request.password())); // Re-encode the password if it is updated
        }

        repo.save(existingCustomer);
        return "Customer updated successfully.";
    }
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = repo.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Customer not found with email: " + email));
        return mapper.toCustomerResponse(customer);
    }
}