package com.java.TrainningJV.services;

import com.java.TrainningJV.dtos.request.ProductRequest;
import com.java.TrainningJV.models.Product;

public interface ProductService {
    Product getProductById(Integer id);

    Integer addProduct(ProductRequest productRequest);

    void deleteProduct(Integer id);

    int updateProduct(Integer id, ProductRequest productRequest);

}
