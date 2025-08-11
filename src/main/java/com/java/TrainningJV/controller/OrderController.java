package com.java.TrainningJV.controller;

import com.java.TrainningJV.dtos.request.OrderRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.java.TrainningJV.dtos.response.ApiResponse;
import com.java.TrainningJV.services.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j(topic = "ORDER-CONTROLLER")
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ApiResponse getOderById(@PathVariable int id){
        log.info("get order by id: {}", id);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("get order sucessfully")
                .data(orderService.findOrderById(id))
        .build();
    }

    @PostMapping("")
    public ApiResponse createOrder(@Valid @RequestBody OrderRequest request){
        log.info("create order: {}", request);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("create order successfully")
                .data(orderService.createOrder(request))
                .build();
    }


    @GetMapping("/oders-userid/{userId}")
    public ApiResponse getOrderByUserId(@PathVariable int userId){
        log.info("get order by id: {}", userId);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("get order by id successfully")
                .data(orderService.findOrderByUserId(userId))
                .build();
    }

    @PutMapping("/{orderId}")
    public ApiResponse updateOrder(@Valid @PathVariable int orderId,@Valid @RequestBody OrderRequest request){
        log.info("update order: {}", request);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("update order successfully")
                .data(orderService.updateOrder(orderId, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteOrder(@Valid @PathVariable int id){
        log.info("delete order by id: {}", id);
        orderService.deleteOrder(id);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("delete order successfully")
                .data(id)
                .build();
    }
}
