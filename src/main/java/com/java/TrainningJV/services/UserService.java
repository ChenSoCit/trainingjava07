package com.java.TrainningJV.services;

import com.java.TrainningJV.dtos.request.UserRequest;
import com.java.TrainningJV.dtos.response.RoleCountResponse;
import com.java.TrainningJV.dtos.response.RoleCountResponse;
import com.java.TrainningJV.models.User;

import java.util.List;

public interface  UserService {
    User getUser(Long id);

    long createUser(UserRequest userRequest);

    int updateUser(Long id, UserRequest userRequest);

    int deleteUser(Long id);

    List<User> getUserNoneRole();

    List<User> getUserRole(Long roleId);

    List<RoleCountResponse> getRoleCount();

    List<User> getAllUsers(int page, int size);
}
