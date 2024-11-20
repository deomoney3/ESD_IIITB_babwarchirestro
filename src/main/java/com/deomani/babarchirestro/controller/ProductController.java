package com.deomani.babarchirestro.controller;

import com.deomani.babarchirestro.dto.CustomerRequest;
import com.deomani.babarchirestro.dto.ProductRequest;
import com.deomani.babarchirestro.entity.Product;
import com.deomani.babarchirestro.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest request,@PathVariable String name) {
        return ResponseEntity.ok(productService.updateProduct(request,name));
    }

}
