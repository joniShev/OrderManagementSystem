package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.entity.Order;
import com.example.ordermanagementsystem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order updateOrder(Long orderId, Order orderDetails) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setDescription(orderDetails.getDescription());
            order.setTotalPrice(orderDetails.getTotalPrice());
            return orderRepository.save(order);
        }
        return null;
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
