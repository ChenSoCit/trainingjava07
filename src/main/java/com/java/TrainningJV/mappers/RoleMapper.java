package com.java.TrainningJV.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.TrainningJV.models.Role;
import com.java.TrainningJV.models.RoleExample;

public interface RoleMapper {
    
    long countByExample(RoleExample example);

    
    int deleteByExample(RoleExample example);

   
    int deleteByPrimaryKey(Integer id);

    
    int insert(Role row);

    
    int insertSelective(Role row);

    
    List<Role> selectByExample(RoleExample example);

    
    Role selectByPrimaryKey(Integer id);

    
    int updateByExampleSelective(@Param("row") Role row, @Param("example") RoleExample example);

    
    int updateByExample(@Param("row") Role row, @Param("example") RoleExample example);

    
    int updateByPrimaryKeySelective(Role row);

    
    int updateByPrimaryKey(Role row);


}