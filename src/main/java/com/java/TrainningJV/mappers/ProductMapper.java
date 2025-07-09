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

    //delete by id
    int deleteByPrimaryKey(Long id);

    // insert
    int insert(Product product);


    int insertSelective(Product row);

    List<Product> selectByExample(ProductExample example);

    // get product by id
    Product selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Product row, @Param("example") ProductExample example);

    int updateByExample(@Param("row") Product row, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product row);

    int updateByPrimaryKey(Product row);
}