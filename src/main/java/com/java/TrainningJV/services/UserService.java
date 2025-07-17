package com.java.TrainningJV.services;

import java.util.List;

import com.java.TrainningJV.dtos.request.UserRequest;
import com.java.TrainningJV.dtos.response.RoleCountResponse;
import com.java.TrainningJV.dtos.response.UserWithOrderResponse;
import com.java.TrainningJV.models.User;

public interface  UserService {
    
    User getUser(Integer id);

    int createUser(UserRequest userRequest);

    int updateUser(Integer id, UserRequest userRequest);

    int deleteUser(Integer id);

    List<User> getUserNoneRole();

    List<User> getUserRole(Integer roleId);

    List<RoleCountResponse> getRoleCount();

    // list all users with their orders
    List<UserWithOrderResponse> getAllUsersWithOrders();

    // get user with orders by id
    User getUserWithOrders(Integer id);

    // List<User> getAllUsers(int page, int size);
}
