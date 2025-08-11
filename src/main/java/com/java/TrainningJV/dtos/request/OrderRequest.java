package com.java.TrainningJV.dtos.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    
    private Integer userId;

    
    private String fullname;

    
    private String email;

    
    private String phone;

   
    private String address;

    private String status;

    
    private BigDecimal totalMoney;
}
