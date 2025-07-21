package com.java.TrainningJV.controller;

import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.TrainningJV.dtos.request.UserRequest;
import com.java.TrainningJV.exceptions.ResourceNotFoundException;
import com.java.TrainningJV.models.User;
import com.java.TrainningJV.services.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j(topic="CONTROLLER-TEST")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserRequest userRequest;
    private User userTest;
    private User existingUser;

    @BeforeEach
    void initData(){
        userRequest =  UserRequest.builder()
        .firstName("test1")
        .lastName("ok")
        .email("test1@gmail.com")
        .address("HN")
        .gender("male")
        .dateOfBirth("1990-1-1")
        .phoneNumber("0987654321")
        .password("12345678")
        .roleId(1)
        .build();

        userTest = User.builder()
        .id(1)
        .firstName("test1")
        .lastName("ok")
        .email("test1@gmail.com")
        .address("HN")
        .gender("male")
        .dateOfBirth(new Date())
        .phone("0987654321")
        .password("12345678")
        .roleId(1)
        .build();

        existingUser = User.builder()
            .id(1)
            .firstName("Old")
            .lastName("User")
            .email("old@example.com")
            .password("oldpass")
            .address("Old Address")
            .phone("0123456789")
            .gender("female")
            .dateOfBirth(new Date())
            .roleId(1)
            .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // gia lap tra ve id: 100
        when(userService.createUser(any(UserRequest.class))).thenReturn(100);

        mockMvc.perform(MockMvcRequestBuilders  
                .post("/api/users")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(userRequest))) // chuyen json ve string
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User created successfully"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(100));

        verify(userService, times(1)).createUser(any(UserRequest.class));
    }  
    
    @Test
    void createUser_invalidEmailFormat_shouldReturnBadRequest() throws Exception {
    // Email không đúng định dạng
    userRequest.setEmail("invalid-email");

    mockMvc.perform(MockMvcRequestBuilders
            .post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userRequest)))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("email: Invalid email format"));
        
    verify(userService, never()).createUser(any(UserRequest.class));
    }

    @Test
    void getUser_success() throws Exception{
        when(userService.getUser(1)).thenReturn(userTest);

        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("get user sc successfully"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data.firstName").value("test1"));

        verify(userService, times(1)).getUser(1);
    }

    @Test
    void getUser_NotFoundId_fail() throws Exception{
        when(userService.getUser(999)).thenThrow(new ResourceNotFoundException("User", "id", 999));
        
        mockMvc.perform(MockMvcRequestBuilders
        .get("/api/users/999"))

        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User User not found with id = 999"));

        verify(userService).getUser(999);
    }

    @Test
    void updateUser_Success() throws Exception{
        when(userService.updateUser(eq(1),any(UserRequest.class))).thenReturn(1);
        

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(userRequest)))

        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User updated successfully"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(1));

        verify(userService,  times(1)).updateUser(eq(1), any(UserRequest.class));
    }

    @Test
    void updateUser_userNotFound() throws Exception{
        when(userService.updateUser(eq(999), any(UserRequest.class))).thenThrow(new ResourceNotFoundException("User","id", 999));

         mockMvc.perform(MockMvcRequestBuilders.put("/api/users/999")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(userRequest)))

        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User User not found with id = 999"));

        verify(userService, times(1)).updateUser(eq(999), any(UserRequest.class));
    
    }
}
