package com.deomani.babarchirestro.mapper;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.dto.CustomerResponse;
import com.deomani.babarchirestro.dto.ProductRequest;
import com.deomani.babarchirestro.dto.ProductResponse;
import com.deomani.babarchirestro.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }
    public ProductResponse toProductResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}