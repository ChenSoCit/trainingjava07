package com.java.TrainningJV.services;

import com.java.TrainningJV.dtos.request.UserRequest;
import com.java.TrainningJV.dtos.response.RoleCountResponse;
import com.java.TrainningJV.models.User;

import java.util.List;

public interface  UserService {
    User getUser(Integer id);

    int createUser(UserRequest userRequest);

    int updateUser(Integer id, UserRequest userRequest);

    int deleteUser(Integer id);

    List<User> getUserNoneRole();

    List<User> getUserRole(Integer roleId);

    List<RoleCountResponse> getRoleCount();

    // List<User> getAllUsers(int page, int size);
}
