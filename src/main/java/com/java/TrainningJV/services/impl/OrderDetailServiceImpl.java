package com.java.TrainningJV.services.impl;

import com.java.TrainningJV.dtos.request.OrderDetailRequest;
import com.java.TrainningJV.exceptions.ResourceNotFoundException;
import com.java.TrainningJV.mappers.OrderDetailMapper;
import com.java.TrainningJV.mappers.OrderMapper;
import com.java.TrainningJV.models.Order;
import com.java.TrainningJV.models.OrderDetails;
import com.java.TrainningJV.services.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@Slf4j(topic = "ORDER DETAIL SERVICE")
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailMapper orderDetailMapper;
    OrderMapper orderMapper;

    @Override
    public OrderDetails insertOrderDetails(OrderDetailRequest request) {
        log.info("Insert Order Details, {}", request);
        OrderDetails orderDetails = OrderDetails.builder()
                .orderId(request.getOrderId())
                .productId(request.getProductId())
                .price(request.getPrice())
                .numberOfProducts(request.getNumberOfProducts())
                .totalMoney(request.getTotalMoney())
                .color(request.getColor())
                .build();

        int rows = orderDetailMapper.insert(orderDetails);
        if(rows == 0) {
            log.error("Insert Order Details failed");
            throw new RuntimeException("Insert Order Details failed");
        }
        log.info("Insert Order Details successful with ID: {}", orderDetails.getId());
        return orderDetails;
    }

    @Override
    public OrderDetails selectOrderDetailsByOrderId(int id) {
        log.info("Select Order Details, {}", id);
//        Order exitingOrder = orderMapper.selectByPrimaryKey(id);
//        if(exitingOrder == null) {
//            log.error("Select Order Details failed");
//            throw new ResourceNotFoundException("Order", ":", id);
//        }
        return orderDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderDetails> selectAllOrderDetails(int orderId) {
        return List.of();
    }

    @Override
    public long updateOrderDetails(Integer id, OrderDetailRequest request) {
        log.info("Update Order Details, {}", request);

        Double total = request.getPrice() * request.getNumberOfProducts();

        OrderDetails update = OrderDetails.builder()
                .id(id)
                .orderId(request.getOrderId())
                .productId(request.getProductId())
                .price(request.getPrice())
                .numberOfProducts(request.getNumberOfProducts())
                .totalMoney(total)
                .color(request.getColor())
                .build();

        int rows = orderDetailMapper.updateByPrimaryKey(update);
        if (rows == 0) {
            log.warn("No rows updated for order_detail id={}", id);
            throw new RuntimeException("Update Order Details failed");
        }
        log.info("Update Order Details OK, id={}, rows={}", id, rows);
        return rows;
    }

    @Override
    public void deleteByOrderId(int OrderId) {

    }

    @Override
    public void deleteById(int id) {
        log.info("Delete Order Details, {}", id);
        orderDetailMapper.deleteByPrimaryKey(id);
    }
}
