package com.java.TrainningJV.services.impl;

import org.springframework.stereotype.Service;

import com.java.TrainningJV.dtos.request.OrderRequest;
import com.java.TrainningJV.exceptions.ResourceNotFoundException;
import com.java.TrainningJV.mappers.OrderMapper;
import com.java.TrainningJV.mappers.UserMapper;
import com.java.TrainningJV.mappers.mapperCustom.OrderMapperCustom;
import com.java.TrainningJV.models.Order;
import com.java.TrainningJV.models.User;
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

    @Override
    public Order createOrder(OrderRequest orderRequest) {
       
        log.info("created order order request, {}", orderRequest);

        User exitingUser = userMapper.selectByPrimaryKey(orderRequest.getUserId());
        if (exitingUser == null) {
            throw new ResourceNotFoundException("User","id", orderRequest.getUserId());
        }

        Order newOrder = Order.builder()
            .userId(orderRequest.getUserId())
            .fullName(orderRequest.getFullname())
            .email(orderRequest.getEmail())
            .phone(orderRequest.getPhone())
            .address(orderRequest.getAddress())
            .status(orderRequest.getStatus())
            .totalMoney(orderRequest.getTotalMoney())
        .build();

        return orderMapper.insert(newOrder);
    }

    

    @Override
    public Order findOrderById(Integer id) {
        
        log.info("find order by order id");
        Order order = orderMapper.selectByPrimaryKey(id);
        
        return order;
    }

    @Override
    public Order findOrderByUserId(Integer userId) {
        log.info("find order by user id: {}", userId);

        
        User exitingUser = userMapper.selectByPrimaryKey(userId);
        if (exitingUser == null) {
            throw new ResourceNotFoundException("User","id", userId);
        }

        return orderMapperCustom.findOrderByUserId(userId);
        
    }

    @Override
    public int updateOrder(Integer id, OrderRequest request) {
        log.info("update order by id {}", request);

        Order exitingOrder = orderMapper.selectByPrimaryKey(id);
        if (exitingOrder == null) {
            throw new ResourceNotFoundException("Order", "id", id);
        }

        Order upDateOrder = Order.builder()
            .fullName(request.getFullname())
            .email(request.getEmail())
            .phone(request.getPhone())
            .address(request.getAddress())
            .status(request.getStatus())
            .totalMoney(request.getTotalMoney())
        .build();

        return orderMapper.updateByPrimaryKey(upDateOrder);
        
    }

    @Override
    public void deleteOrder(Integer id) {
        log.info("delete order id {}", id);

        int deleteOrder = orderMapper.deleteByPrimaryKey(id);
        if(deleteOrder == 1){
            log.info("deleted order successfull", id);
        }
    }
    
}
