package com.deomani.babarchirestro.service;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.entity.Customer;
import com.deomani.babarchirestro.mapper.CustomerMapper;
import com.deomani.babarchirestro.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
