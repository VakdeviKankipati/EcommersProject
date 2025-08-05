package com.vakya.orderservice.services;

import com.vakya.orderservice.dtos.CreateOrderRequestDto;
import com.vakya.orderservice.exception.ProductNotFoundException;
import com.vakya.orderservice.models.Order;

import java.util.List;


public interface OrderService {
    Order createOrder(CreateOrderRequestDto requestDto) throws ProductNotFoundException;
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
}
