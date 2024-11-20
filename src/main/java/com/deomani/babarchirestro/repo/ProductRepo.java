package com.deomani.babarchirestro.repo;

import com.deomani.babarchirestro.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price DESC Limit 2")
    List<Product> findTop2ByPriceBetweenOrderByPrice(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
}