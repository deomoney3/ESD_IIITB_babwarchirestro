package com.deomani.babarchirestro.mapper;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.dto.CustomerResponse;
import com.deomani.babarchirestro.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .address(request.address())
                .city(request.city())
                .pincode(request.pincode())
                .build();
    }
    public CustomerResponse toCustomerResponse(Customer customer) {

        return new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getCity(),
                customer.getPincode()
        );
    }
}
