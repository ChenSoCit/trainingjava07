package com.java.TrainningJV.services.impl;

import org.springframework.stereotype.Service;

import com.java.TrainningJV.dtos.request.ProductRequest;
import com.java.TrainningJV.exceptions.ResourceNotFoundException;
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
    public Product getProductById(Integer id) {
        log.info("Get product by id: " + id);
        Product exitingProduct = productMapper.selectByPrimaryKey(id);
        if (exitingProduct == null) {
            log.info("Product with id: " + id + " not found");
            throw new ResourceNotFoundException("Product", ":", id);
        }

        log.info("Product with id: {} ", id );
        return exitingProduct;
    }

    @Override
    public Integer addProduct(ProductRequest productRequest) {
        log.info("Add product: {} ", productRequest);
       
        Product product = Product.builder()
                .name(productRequest.getNameProduct())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .stockQuantity(productRequest.getStockQuantity())
                .build();
        int result = productMapper.insert(product);  
        if(result > 0) {
            log.info("Product created successfully: {}", product);
            return product.getId(); // Assuming the ID is set after creation
        } else {
            throw new RuntimeException("Failed to create product");
        }
    
    }

    @Override
    public void deleteProduct(Integer id) {
        log.info("Delete product: {} ", id);
        Product exitingProduct = productMapper.selectByPrimaryKey(id);

        if(exitingProduct == null){
            log.info("Product not found: {}", id);
            throw new ResourceNotFoundException("Product", ":", id);
        }
        productMapper.deleteByPrimaryKey(id);
        // nt result = productMapper.deleteByPrimaryKey(id);
        // if (result > 0) {
        //     log.info("Product deleted successfully");
        // }else {
        //     throw new RuntimeException("Product delete failed");
        // }

    }

    @Override
    public int updateProduct(Integer id, ProductRequest productRequest) {
        log.info("Update product with id: {} and request: {}", id, productRequest);
        Product existingProduct = productMapper.selectByPrimaryKey(id);
        if (existingProduct == null) {
           throw new ResourceNotFoundException("Product", ":", id);
        }
        
        Product updatedProduct = Product.builder()
                .id(id)
                .name(productRequest.getNameProduct())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .stockQuantity(productRequest.getStockQuantity())
                .build();
        
        log.info("Updated product: {}", updatedProduct);
        return productMapper.updateByPrimaryKey(updatedProduct);
    }
}
