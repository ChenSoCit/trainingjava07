package com.java.TrainningJV.services.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.TrainningJV.dtos.request.UserRequest;
import com.java.TrainningJV.dtos.response.RoleCountResponse;
import com.java.TrainningJV.dtos.response.UserWithOrderResponse;
import com.java.TrainningJV.mappers.UserMapper;
import com.java.TrainningJV.mappers.UserMapperCustom;
import com.java.TrainningJV.models.User;
import com.java.TrainningJV.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j(topic = "UserServiceImpl")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserMapperCustom userMapperCustom;

	@Override
	public User getUser(Integer id) {
		log.info("calling getUser with id: {}", id);
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            log.warn("User with id {} not found", id);
            return null;
        }
        log.info("User found: {}", user);
        return user;
	}

    @Override
    public int createUser(UserRequest userRequest) {
        log.info("Creating user with request: {}", userRequest);
        User users = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .address(userRequest.getAddress())
                .dateOfBirth(Date.valueOf(userRequest.getDateOfBirth()))
                .gender(userRequest.getGender())
                .phone(userRequest.getPhoneNumber())
                .roleId(userRequest.getRoleId())
                .build();

        int result = userMapper.insert(users);
        if (result > 0) {
            log.info("User created successfully: {}", users);
            return users.getId(); // Assuming the ID is set after creation
        } else {
            throw new RuntimeException("Failed to create user");
        }
    }

    @Override
    public int updateUser(Integer id, UserRequest userRequest) {
        log.info("Updating user with id: {} and request: {}", id, userRequest);
        User existingUser = userMapper.selectByPrimaryKey(id);
        if (existingUser == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        User updatedUser = User.builder()
            .id(id)
            .firstName(userRequest.getFirstName())
            .lastName(userRequest.getLastName())
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .address(userRequest.getAddress())
            .phone(userRequest.getPhoneNumber())
            .gender(userRequest.getGender())
            .dateOfBirth(Date.valueOf(userRequest.getDateOfBirth()))
            .roleId(userRequest.getRoleId())  
            .build(); 

        return userMapper.updateByPrimaryKey(updatedUser);
    }

    @Override
    public int deleteUser(Integer id) {
    log.info("Deleting user with id: {}", id);

    User existingUser = userMapper.selectByPrimaryKey(id);
    if (existingUser == null) {
        throw new RuntimeException("User not found with id: " + id);
    }

    int deletedRows = userMapper.deleteByPrimaryKey(id);
    if (deletedRows <= 0) {
        throw new RuntimeException("Failed to delete user with id: " + id);
    }

    log.info("User deleted successfully with id: {}", id);
    return deletedRows;
    }

    @Override
    public List<User> getUserNoneRole() {
        log.info("calling getUserNoneRole");
        return userMapperCustom.getUserNoneRole();
    }

    @Override
    public List<User> getUserRole(Integer roleId) {
        log.info("calling getUserRole with roleId: {}", roleId);
        return userMapperCustom.getUserRole(roleId);
    }

    @Override
    public List<RoleCountResponse> getRoleCount() {
        log.info("calling getRoleCount");
        return userMapperCustom.countUserRole();
    }

    @Override
    public List<UserWithOrderResponse> getAllUsersWithOrders() {
        log.info("calling getAllUsersWithOrders");

        return userMapperCustom.getUsersWithOrders();
        
    }

    @Override
    @Transactional()
    public User getUserWithOrders(Integer id) {
        log.info("calling getUserWithOrders with id: {}", id);
        User existingUser = userMapper.selectByPrimaryKey(id);
        if (existingUser == null) {
            log.warn("User with id {} not found", id);
            return null;
        }
        User userWithOrders = userMapperCustom.getUserWithOrders(id);
        if (userWithOrders == null) {
            log.warn("User with orders for id {} not found", id);
            return null;
        }
        return  userWithOrders;

    }

    // @Override
    // public List<User> getAllUsers(int page, int size) {
    //     return List.of();
    // }

}
