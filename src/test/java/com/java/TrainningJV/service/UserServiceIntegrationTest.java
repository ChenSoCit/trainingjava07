package com.java.TrainningJV.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.java.TrainningJV.mappers.UserMapper;
import com.java.TrainningJV.models.User;
import com.java.TrainningJV.services.UserService;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceIntegrationTest{
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetUser_ExistingId_Success() {
        // Given: Insert user vào DB thật qua mapper
        User newUser = User.builder()
                .firstName("Nguyen")
                .lastName("Tra")
                .address("HN")
                .dateOfBirth(new Date())
                .email("tra@gmail.com")
                .gender("male")
                .password("1234567")
                .phone("0987654321")
                .roleId(1)
                .build();

        userMapper.insert(newUser); // ID sẽ được DB tự sinh

        // When: Gọi service
        User result = userService.getUser(newUser.getId());

        // Then: Kiểm tra kết quả
        assertNotNull(result);
        assertEquals("Nguyen", result.getFirstName());
    }

}
