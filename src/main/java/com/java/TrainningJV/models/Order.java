package com.java.TrainningJV.models;

import java.math.BigDecimal;
import java.util.Date;

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
public class Order {
    
    private Integer id;

    
    private Integer userId;

   
    private String fullname;

    
    private String email;

    
    private String phone;

    
    private String address;

    
    private Date orderDate;

    
    private String status;

    
    private BigDecimal totalMoney;

    
}