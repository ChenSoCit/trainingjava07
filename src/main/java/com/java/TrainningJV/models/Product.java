package com.java.TrainningJV.models;
import java.time.LocalDateTime;
import java.util.Locale.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    
    private Integer id;

    
    private String name;

    
    private String description;

    
    private double price;

    private Integer stockQuantity;

    
    private LocalDateTime createdAt;

    
    private LocalDateTime updatedAt;

    private Integer categoryId;
    
    private Category category;
    
}