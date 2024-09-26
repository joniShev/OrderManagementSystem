package com.example.ordermanagementsystem.service;

import com.example.ordermanagementsystem.dto.OrderDTO;
import com.example.ordermanagementsystem.entity.Order;
import com.example.ordermanagementsystem.mapper.OrderMapper;
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

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);
        order.setCreatedAt(LocalDateTime.now());
        return OrderMapper.INSTANCE.orderToOrderDTO(orderRepository.save(order));
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper.INSTANCE::orderToOrderDTO)
                .toList();
    }

    public OrderDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(OrderMapper.INSTANCE::orderToOrderDTO)
                .orElse(null);
    }

    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        return orderRepository.findById(orderId).map(order -> {
            order.setDescription(orderDTO.getDescription());
            order.setTotalPrice(orderDTO.getTotalPrice());
            Order updatedOrder = orderRepository.save(order);
            return OrderMapper.INSTANCE.orderToOrderDTO(updatedOrder);
        }).orElse(null);
    }

    public boolean deleteOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }
}
