package com.java.TrainningJV.mappers.mapperCustom;

import com.java.TrainningJV.models.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapperCustom {
    List<Product> getAllProducts();

    int updateStock (@Param("productId") Integer productId, @Param("qty") int qty);

    int updateIncreaseStock(@Param("productId") Integer productId, @Param("qty") int qty);

}
