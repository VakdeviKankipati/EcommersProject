package com.vakya.orderservice.services;

import ch.qos.logback.core.joran.sanity.Pair;
import com.vakya.orderservice.dtos.CreateOrderRequestDto;
import com.vakya.orderservice.dtos.Product;
import com.vakya.orderservice.exception.ProductNotFoundException;
import com.vakya.orderservice.models.Order;
import com.vakya.orderservice.repositories.OrderDetailRepository;
import com.vakya.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private RestTemplate restTemplate;


    public OrderServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Order createOrder(CreateOrderRequestDto requestDto) throws ProductNotFoundException {
        // Fetch product details from ProductService using RestTemplate
        Product product = restTemplate.getForObject(
                "http://productservice/products/" + requestDto.getProductId(),
                Product.class
        );

        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }

        // Calculate total price
        double totalPrice = product.getPrice() * requestDto.getQuantity();

        // Create and save order
        Order order = new Order();
        order.setProductId(requestDto.getProductId());
        order.setQuantity(requestDto.getQuantity());
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

}
