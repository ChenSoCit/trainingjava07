package com.java.TrainningJV.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.TrainningJV.dtos.request.OrderRequest;
import com.java.TrainningJV.exceptions.BadRequestException;
import com.java.TrainningJV.exceptions.ResourceNotFoundException;
import com.java.TrainningJV.mappers.OrderDetailMapper;
import com.java.TrainningJV.mappers.OrderMapper;
import com.java.TrainningJV.mappers.UserMapper;
import com.java.TrainningJV.mappers.mapperCustom.OrderMapperCustom;
import com.java.TrainningJV.models.Order;
import com.java.TrainningJV.models.User;
import com.java.TrainningJV.models.enums.OrderStatus;
import com.java.TrainningJV.services.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j(topic = "ORDER-SERVICE")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final OrderMapperCustom orderMapperCustom;
    private final OrderDetailMapper orderDetailMapper;

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        log.info("created order order request, {}", orderRequest);
        User exitingUser = userMapper.selectByPrimaryKey(orderRequest.getUserId());
        if (exitingUser == null) {
            throw new ResourceNotFoundException("User","id", orderRequest.getUserId());
        }

        Order newOrder = Order.builder()
            .userId(orderRequest.getUserId())
            .fullName(orderRequest.getFullName())
            .email(orderRequest.getEmail())
            .phone(orderRequest.getPhone())
            .address(orderRequest.getAddress())
            .status(OrderStatus.pending)
            .orderDate(new Date())
    
        .build();
        int rows = orderMapper.insert(newOrder);
        if (rows != 1) throw new IllegalStateException("Insert failed");

        return newOrder;
    }

    @Override
    public Order findOrderById(Integer id) {
        log.info("find order by order id");
        Order existingOrder = orderMapper.selectByPrimaryKey(id);
        if (existingOrder == null) {
            throw new ResourceNotFoundException("Order", "id", id);
        }
        return existingOrder;
    }

    @Override
    public Order updateOrder(Integer id, OrderRequest request) {
        log.info("Updating order with id = {}, request = {}", id, request);

        Order existingOrder = orderMapper.selectByPrimaryKey(id);
        if (existingOrder == null) {
            throw new ResourceNotFoundException("Order", "id", id);
        }

        // Xác định status mới
        OrderStatus newStatus;
        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            try {
                newStatus = OrderStatus.valueOf(request.getStatus().trim());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid status value: " + request.getStatus());
            }
        } else {
            // Giữ nguyên status cũ nếu không gửi
            newStatus = existingOrder.getStatus();
        }

        // Tạo entity mới để update, giữ lại các giá trị không đổi
        Order updateOrder = Order.builder()
                .id(id)  // bắt buộc có id để update
                .userId(existingOrder.getUserId()) // giữ nguyên
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .orderDate(existingOrder.getOrderDate()) // giữ nguyên ngày tạo
                .status(newStatus)
                .build();

        int rows = orderMapper.updateByPrimaryKey(updateOrder);
        if (rows == 0) {
            log.warn("Update failed for order id = {}", id);
            throw new BadRequestException("Update failed for order id = " + id);
        }
        return updateOrder;
    }


    @Override
    @Transactional
    public void deleteOrder(Integer id) {
        // Kiểm tra tồn tại
        Order existing = orderMapper.selectByPrimaryKey(id);
        if (existing == null) {
            log.info("Order with id: {} not found", id);
            throw new ResourceNotFoundException("Order", "id", id);
        }
        // Xóa các order details liên quan
        orderDetailMapper.deleteByOrderId(id);
       
        // Xóa order
        log.info("Deleting order with id: {}", id);
        int rowsOrder = orderMapper.deleteByPrimaryKey(id);
        if (rowsOrder == 0) {
            log.warn("Delete failed for order id = {}", id);
            throw new BadRequestException("Delete failed for order id = " + id);
        }

    }


    @Override
    public List<Order> getOrders( ) {
        log.info("Get all orders");
        return orderMapperCustom.findAllOrder();
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        log.info("Get orders by user id: {}", userId);
        User existingUser = userMapper.selectByPrimaryKey(userId);
        if (existingUser == null) {
            log.info("User not found with id: {}", userId);
            throw new ResourceNotFoundException("User", "id", userId);
        }   
        List<Order> orders = orderMapperCustom.findOrderByUserId(userId);
        return orders;
    }
    
}
