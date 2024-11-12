package com.deomani.babarchirestro.repo;

import com.deomani.babarchirestro.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
