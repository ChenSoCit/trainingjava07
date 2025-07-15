package com.java.TrainningJV.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.java.TrainningJV.dtos.request.UserRequest;
import com.java.TrainningJV.dtos.response.ApiResponse;
import com.java.TrainningJV.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j(topic = "UserController")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable int id) {
        log.info("Fetching user details: {}", id);

        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("get user sc successfully")
                .data(userService.getUser(id)) 
                .build();
    }


    @GetMapping("/none-role")
    public ApiResponse getUserNoneRole(@RequestParam int page,
                                       @RequestParam int size) {
        log.info("Fetching user none role");
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("get user none role")
                .data(userService.getUserNoneRole())
                .build();
    }

    @GetMapping("/roles/{roleId}")
    public ApiResponse getUserByRole(@PathVariable Integer roleId){
        log.info("Fetching user by role: {}", roleId);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("get user by role")
                .data(userService.getUserRole(roleId))
                .build();
    }

    @GetMapping("/role-count")
    public ApiResponse getCountRole(){
        log.info("Fetching count role");
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("get count role")
                .data(userService.getRoleCount())
                .build();
    }

    @PostMapping("")
    public ApiResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        log.info("Creating user: {}", userRequest);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("User created successfully")
                .data(userService.createUser(userRequest))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable int id, @Valid @RequestBody UserRequest userRequest) {
        log.info("Updating user with id: {} and request: {}", id, userRequest);
        int result = userService.updateUser(id, userRequest);
        if (result > 0) {
            return ApiResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("User updated successfully")
                    .data(null)
                    .build();
        } else {
            return ApiResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("User not found")
                    .data(null)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable int id) {
        log.info("Deleting user with id: {}", id);
        int result = userService.deleteUser(id);
        if (result > 0) {
            return ApiResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("User deleted successfully")
                    .data(null)
                    .build();
        } else {
            return ApiResponse.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("User not found")
                    .data(null)
                    .build();
        }
    }
}
