package com.java.TrainningJV.services;

import com.java.TrainningJV.dtos.request.ProductRequest;
import com.java.TrainningJV.models.Product;

public interface ProductService {
    Product getProductById(Long id);

    long addProduct(ProductRequest productRequest);

    void deleteProduct(Long id);
}
