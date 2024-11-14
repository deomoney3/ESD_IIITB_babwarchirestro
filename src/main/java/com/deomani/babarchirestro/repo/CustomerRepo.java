package com.deomani.babarchirestro.repo;

import com.deomani.babarchirestro.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    // Custom query method to find a customer by email
    Optional<Customer> findByEmail(String email);
}
