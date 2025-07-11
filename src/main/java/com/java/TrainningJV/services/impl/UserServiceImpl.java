package com.java.TrainningJV.services.impl;

import com.java.TrainningJV.dtos.response.RoleCountResponse;
import org.springframework.stereotype.Service;

import com.java.TrainningJV.dtos.request.UserRequest;
import com.java.TrainningJV.mappers.UserMapper;
import com.java.TrainningJV.models.User;
import com.java.TrainningJV.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.List;


@Service
@Slf4j(topic = "UserServiceImpl")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

	@Override
	public User getUser(Long id) {
		log.info("calling getUser with id: {}", id);
        User user = userMapper.getUserById(id);
        if (user == null) {
            log.warn("User with id {} not found", id);
            return null;
        }
        log.info("User found: {}", user);
        return user;
	}

    @Override
    public long createUser(UserRequest userRequest) {
        log.info("Creating user with request: {}", userRequest);
        User users = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .address(userRequest.getAddress())
                .dateOfBirth(Date.valueOf(userRequest.getDateOfBirth()))
                .gender(userRequest.getGender())
                .phoneNumber(userRequest.getPhoneNumber())
                .roleId(userRequest.getRoleId())
                .build();

        int result = userMapper.createUser(users);
        if (result > 0) {
            log.info("User created successfully: {}", users);
            return users.getId(); // Assuming the ID is set after creation
        } else {
            throw new RuntimeException("Failed to create user");
        }
    }

    @Override
    public int updateUser(Long id, UserRequest userRequest) {
        log.info("Updating user with id: {} and request: {}", id, userRequest);
        User existingUser = userMapper.getUserById(id);
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
            .gender(userRequest.getGender())
            .dateOfBirth(Date.valueOf(userRequest.getDateOfBirth()))
            .roleId(userRequest.getRoleId())  // Thêm dòng này!
            .build(); 

        return userMapper.updateUser(updatedUser);
    }

    @Override
    public int deleteUser(Long id) {
    log.info("Deleting user with id: {}", id);

    User existingUser = userMapper.getUserById(id);
    if (existingUser == null) {
        throw new RuntimeException("User not found with id: " + id);
    }

    int deletedRows = userMapper.deleteUser(id);
    if (deletedRows <= 0) {
        throw new RuntimeException("Failed to delete user with id: " + id);
    }

    log.info("User deleted successfully with id: {}", id);
    return deletedRows;
}

    @Override
    public List<User> getUserNoneRole() {
        log.info("calling getUserNoneRole");
        return userMapper.getUserNoneRole();
    }

    @Override
    public List<User> getUserRole(Long roleId) {
        log.info("calling getUserRole with roleId: {}", roleId);
        return userMapper.getUserRole(roleId);
    }

    @Override
    public List<RoleCountResponse> getRoleCount() {
        log.info("calling getRoleCount");
        return userMapper.countUserRole();
    }

    @Override
    public List<User> getAllUsers(int page, int size) {
        return List.of();
    }

}
