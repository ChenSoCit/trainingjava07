package com.java.TrainningJV.services.impl;

import org.springframework.stereotype.Service;

import com.java.TrainningJV.dtos.request.ProductRequest;
import com.java.TrainningJV.mappers.ProductMapper;
import com.java.TrainningJV.models.Product;
import com.java.TrainningJV.services.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j(topic = "PRODUCT-SERVICE")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public Product getProductById(Long id) {
        log.info("Get product by id: " + id);
        Product exitingProduct = productMapper.selectByPrimaryKey(id);
        if (exitingProduct == null) {
            log.info("Product with id: " + id + " not found");
            return null;
        }
        log.info("Product with id: {} ", id );
        return exitingProduct;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Add product: {} ", productRequest);
       
        Product product = Product.builder()
                .name(productRequest.getNameProduct())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .stockQuantity(productRequest.getStockQuantity())
                .build();
        int result = productMapper.insert(product);
        if (result > 0) {
            log.info("Product added successfully");
            return product.getId();
        }else {
            throw new RuntimeException("Product add failed");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Delete product: {} ", id);
        Product exitingProduct = productMapper.selectByPrimaryKey(id);
        if (exitingProduct == null) {
            log.info("Product with id: " + id + " not found");
        }
        int result = productMapper.deleteByPrimaryKey(id);
        if (result > 0) {
            log.info("Product deleted successfully");
        }else {
            throw new RuntimeException("Product delete failed");
        }

    }
}
