package com.java.TrainningJV.services;

import com.java.TrainningJV.dtos.request.OrderDetailRequest;
import com.java.TrainningJV.models.OrderDetails;

import java.util.List;

public interface OrderDetailService {
    OrderDetails insertOrderDetails(OrderDetailRequest request);

    OrderDetails selectOrderDetailsByOrderId(int id);

    List<OrderDetails> selectAllOrderDetails(int orderId);

    long updateOrderDetails(Integer id,OrderDetailRequest request);

    void deleteByOrderId(int OrderId);
    void deleteById(int id);
}
