package com.java.TrainningJV.dtos.response;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class OrderResponseToUser  {
    private Integer orderId;
    private BigDecimal totalMoney;
    private Date orderDate;
}
