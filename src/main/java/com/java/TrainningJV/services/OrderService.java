package com.java.TrainningJV.services;

import java.util.List;

import com.java.TrainningJV.dtos.request.OrderRequest;
import com.java.TrainningJV.models.Order;

public interface  OrderService {

    List<Order> getOrders();

    List<Order> getOrdersByUserId(Integer userId);

    Order createOrder(OrderRequest orderRequest);
    
    Order findOrderById(Integer id);

    Order updateOrder(Integer id, OrderRequest orderRequest);

    void deleteOrder(Integer id);
    
}
