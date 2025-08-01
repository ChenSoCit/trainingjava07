package com.java.TrainningJV.mappers;

import com.java.TrainningJV.models.Order;
import com.java.TrainningJV.models.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
   
    long countByExample(OrderExample example);

    
    int deleteByExample(OrderExample example);

    
    int deleteByPrimaryKey(Integer id);

    int insert(Order row);

    
    int insertSelective(Order row);

    
    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Order row, @Param("example") OrderExample example);

    
    int updateByExample(@Param("row") Order row, @Param("example") OrderExample example);


    int updateByPrimaryKeySelective(Order row);

    
    int updateByPrimaryKey(Order row);
}