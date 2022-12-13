package com.codecool.risque.controller;


import com.codecool.risque.exception.ResourceNotFoundException;
import com.codecool.risque.model.Product;
import com.codecool.risque.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // get all products
    @GetMapping("products")
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    // get product by id

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
        throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id : : " + productId));

        return ResponseEntity.ok().body(product);
    }

    @PostMapping("products")
    public Product createProduct(@RequestBody Product product){
        return this.productRepository.save(product);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
        @Validated @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id : : " + productId));
    product.setName(productDetails.getName());
    product.setPrice(productDetails.getPrice());
    product.setDescription(productDetails.getDescription());
    product.setImageUrl(productDetails.getImageUrl());
    return ResponseEntity.ok(this.productRepository.save(product));
    }

    @DeleteMapping("products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id : : " + productId));

    this.productRepository.delete(product);
    Map <String,Boolean> response = new HashMap<>();
    response.put("deleted",Boolean.TRUE);
    return response;
    }

}
