package com.java.TrainningJV.mappers;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.TrainningJV.models.User;
import com.java.TrainningJV.models.UserExample;

public interface UserMapper {
   
    long countByExample(UserExample example);

    
    int deleteByExample(UserExample example);

    // delete user by id
    int deleteByPrimaryKey(Integer id);

    // create a new user
    int insert(User row);

    
    int insertSelective(User row);

    
    List<User> selectByExample(UserExample example);

    // select user detail by id
    User selectByPrimaryKey(Integer id);

    
    int updateByExampleSelective(@Param("row") User row, @Param("example") UserExample example);

    
    int updateByExample(@Param("row") User row, @Param("example") UserExample example);

    
    int updateByPrimaryKeySelective(User row);

    // update user detail by id
    int updateByPrimaryKey(User row);

    

}