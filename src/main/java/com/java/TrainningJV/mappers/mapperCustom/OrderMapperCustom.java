package com.java.TrainningJV.mappers.mapperCustom;

import com.java.TrainningJV.models.Order;

import java.util.List;

public interface OrderMapperCustom {
    Order findOrderByUserId(int userId);

    List<Order> findAllOrder();
}
