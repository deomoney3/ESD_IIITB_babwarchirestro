package com.deomani.babarchirestro.service;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.entity.Customer;
import com.deomani.babarchirestro.mapper.CustomerMapper;
import com.deomani.babarchirestro.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.deomani.babarchirestro.dto.LoginRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor


public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
    public boolean loginCustomerWithoutEncryption(LoginRequest request) {
        // Use Optional<Customer> to correctly handle potential null values
        Optional<Customer> optionalCustomer = repo.findByEmail(request.email());
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get(); // Extract the customer from the Optional
            return customer.getPassword().equals(request.password());
        }
        return false;
    }


}