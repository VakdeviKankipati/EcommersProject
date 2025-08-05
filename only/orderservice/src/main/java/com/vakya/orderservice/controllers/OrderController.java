package com.vakya.orderservice.controllers;


import com.vakya.orderservice.dtos.CreateOrderRequestDto;
import com.vakya.orderservice.exception.ProductNotFoundException;
import com.vakya.orderservice.models.Order;
import com.vakya.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequestDto requestDto) {
        try {
            Order order = orderService.createOrder(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }


}
