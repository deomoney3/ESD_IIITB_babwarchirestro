package com.deomani.babarchirestro.service;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.dto.LoginRequest;
import com.deomani.babarchirestro.dto.ProductRequest;
import com.deomani.babarchirestro.entity.Customer;
import com.deomani.babarchirestro.entity.Product;
import com.deomani.babarchirestro.mapper.ProductMapper;
import com.deomani.babarchirestro.repo.ProductRepo;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    public String createProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        productRepo.save(product);
        return "Product Created Successfully";
    }

    public Product updateProduct(ProductRequest request,String name) {
        Product product = productRepo.findByName(name).orElseThrow(() ->
                new RuntimeException("Customer not found with email: " + name)
        );
        product.setName(request.name());
        product.setPrice(request.price());

        return productRepo.save(product);
    }



}
