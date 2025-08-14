package com.java.TrainningJV.dtos.response;

import java.util.Date;
import java.util.List;

import com.java.TrainningJV.models.Order;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserResponse {
    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String roleName;
    private Date dateOfBirth;
    private String password;
    private Date createdAt;
    private Date updatedAt;

    private List<Order> orders;
}
