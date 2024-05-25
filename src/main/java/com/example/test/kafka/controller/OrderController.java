package com.example.test.kafka.controller;

import com.example.test.kafka.model.OrderModel;
import com.example.test.kafka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel orderModel) {
        OrderModel createdOrder = orderService.createOrder(orderModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
