package com.java.TrainningJV.services;

import com.java.TrainningJV.dtos.request.OrderRequest;
import com.java.TrainningJV.models.Order;



public interface  OrderService {

    // List<Order> getOrders();

    Order createOrder(OrderRequest orderRequest);
    
    Order findOrderById(Integer id);

    Order findOrderByUserId(Integer userId);

    int updateOrder(Integer id, OrderRequest orderRequest);

    void deleteOrder(Integer id);
    
}
