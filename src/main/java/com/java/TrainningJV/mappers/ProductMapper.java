package com.java.TrainningJV.mappers;

import com.java.TrainningJV.models.Product;
import com.java.TrainningJV.models.ProductExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ProductMapper {
    
    long countByExample(ProductExample example);

    
    int deleteByExample(ProductExample example);

    
    int deleteByPrimaryKey(Integer id);

    
    int insert(Product row);

    
    int insertSelective(Product row);

    
    List<Product> selectByExample(ProductExample example);

    
    Product selectByPrimaryKey(Integer id);

    
    int updateByExampleSelective(@Param("row") Product row, @Param("example") ProductExample example);

   
    int updateByExample(@Param("row") Product row, @Param("example") ProductExample example);

   
    int updateByPrimaryKeySelective(Product row);

    
    int updateByPrimaryKey(Product row);
}