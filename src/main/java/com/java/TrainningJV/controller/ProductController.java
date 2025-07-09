package com.java.TrainningJV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.TrainningJV.dtos.request.ProductRequest;
import com.java.TrainningJV.dtos.response.ApiResponse;
import com.java.TrainningJV.services.ProductService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j(topic = "PRODUCT-CONTROLLER")
@Validated
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ApiResponse getProductById(@Valid @PathVariable long id) {
        log.info("Get product by id: {}", id);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("get product successfully")
                .data("Product with ID: " + id + " retrieved successfully")
                // .data(productService.getProductById(id))
                .build();
    }

    @PostMapping("")
    public ApiResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
        log.info("Create product: {}", productRequest);
        return ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Created product successfully")
                .data(productService.addProduct(productRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteProduct(@Valid @PathVariable long id) {
        log.info("Delete product: {}", id);
        productService.deleteProduct(id);
        return ApiResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Deleted product successfully")
                .data(id)
                .build();
    }
}
