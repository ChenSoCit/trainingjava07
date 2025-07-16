package com.java.TrainningJV.dtos.response;

import java.util.List;

import lombok.Data;
@Data
public class UserWithOrderResponse {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Integer roleId;
    private List<OrderResponseToUser> orders;
}

