package com.java.TrainningJV.services;

import java.util.List;

import com.java.TrainningJV.dtos.request.ProductRequest;
import com.java.TrainningJV.models.Product;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Integer id);

    Product addProduct(ProductRequest productRequest);

    void deleteProduct(Integer id);

    Product updateProduct(Integer id, ProductRequest productRequest);

}
