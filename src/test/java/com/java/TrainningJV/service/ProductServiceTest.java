package com.java.TrainningJV.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.TrainningJV.exceptions.ResourceNotFoundException;
import com.java.TrainningJV.mappers.CategoryMapper;
import com.java.TrainningJV.mappers.ProductMapper;
import com.java.TrainningJV.mappers.mapperCustom.ProductMapperCustom;
import com.java.TrainningJV.models.Product;
import com.java.TrainningJV.services.impl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "PRODUCT-SERVICE-TEST")
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock private ProductMapper productMapper;
    @Mock private ProductMapperCustom productMapperCustom;
    @Mock private CategoryMapper categoryMapper;

    private ProductServiceImpl productService;

    private Product test1;
    private Product test2;
    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productMapper, productMapperCustom,categoryMapper );

        test1 = new Product();
        test1.setId(1);
        test1.setName("Test Product 1");
        test1.setPrice(BigDecimal.valueOf(100.0));
        test1.setDescription("Description for Test Product 1");
        test1.setStockQuantity(10);
        test1.setCategoryId(1);

        test2 = new Product();
        test2.setId(2);
        test2.setName("Test Product 2");
        test2.setPrice(BigDecimal.valueOf(200.0));
        test2.setDescription("Description for Test Product 2");
        test2.setStockQuantity(20);
        test2.setCategoryId(2);

    }

    @Test
    void testGetProductById_success() {
        when(productMapper.selectByPrimaryKey(1)).thenReturn(test1);
        
        Product product = productService.getProductById(1);
        
        assertNotNull(product);
        assertEquals("Test Product 1", product.getName());
        assertEquals(BigDecimal.valueOf(100.0), product.getPrice());
    }

    @Test
    void testGetProductById_notFound() {
        when(productMapper.selectByPrimaryKey(99)).thenReturn(null);
        
        ResourceNotFoundException exception = 
            assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(99));
        
        assertEquals("Product not found with id: 99", exception.getMessage());
        verify(productMapper).selectByPrimaryKey(99);
        verifyNoMoreInteractions(productMapper);
    }
   
}
