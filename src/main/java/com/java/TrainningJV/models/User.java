package com.java.TrainningJV.models;

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
public class User {
    
    private Integer id;

    
    private String firstName;

    
    private String lastName;

    private String email;

    
    private Date dateOfBirth;

    
    private String gender;


    private String address;

    
    private Date createdAt;


    private Date updatedAt;

    
    private String phone;

    
    private Integer roleId;

    
    private String password;

}