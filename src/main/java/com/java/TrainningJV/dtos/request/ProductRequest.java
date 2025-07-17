package com.java.TrainningJV.dtos.request;

import java.io.Serializable;

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
public class ProductRequest implements Serializable {
    
    private String nameProduct;

    private String description;

    private double price;

    private int stockQuantity;
    
}
