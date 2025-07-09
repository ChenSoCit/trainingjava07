package com.java.TrainningJV.mappers;

import com.java.TrainningJV.dtos.response.RoleCountResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.java.TrainningJV.models.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserById(@Param("id") Long id);

    User findByEmail(String email);

    int createUser(User user);

    int updateUser(User user);

    int deleteUser(@Param("id") Long id);


    List<User> getUserNoneRole();

    List<User> getUserRole(@Param("roleId") Long roleId);

    List<RoleCountResponse> countUserRole();

    List<User> getAllUsers();

}
