package com.java.TrainningJV.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailRequest {
    @NotNull(message = "Order id must be not null")
    private int orderId;

    @NotNull(message = "product must be not null")
    private int productId;
    @NotNull(message = "price must be not null")
    @Min(value = 1)
    private Double price;
    @NotNull(message = "number of product must be not null")
    @Min(value = 1)
    private Integer numberOfProducts;
    @NotNull(message = "total money must be not null")
    @Min(value = 0)
    private Double totalMoney;
    private String color;
}
