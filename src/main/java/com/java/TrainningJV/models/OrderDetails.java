package com.java.TrainningJV.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDetails {
    
    private Integer id;
    
    private Integer orderId;

    private Integer productId;

    
    private Double price;

    
    private Integer numberOfProducts;

    
    private Double totalMoney;

    
    private String color;

}