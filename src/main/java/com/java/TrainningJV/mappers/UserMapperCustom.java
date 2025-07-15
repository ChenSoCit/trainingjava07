package com.java.TrainningJV.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.java.TrainningJV.dtos.response.RoleCountResponse;
import com.java.TrainningJV.models.User;
@Mapper
public interface UserMapperCustom {

    // find user by email
    User findByEmail(String email);

    // Lấy danh sách người dùng không có role
    List<User> getUserNoneRole();

    // Lấy danh sách người dùng theo roleId
    List<User> getUserRole(@Param("roleId") Integer roleId);

    // Đếm số lượng user theo từng role
    List<RoleCountResponse> countUserRole();
}