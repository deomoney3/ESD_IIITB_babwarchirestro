package com.deomani.babarchirestro.repo;

import com.deomani.babarchirestro.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}