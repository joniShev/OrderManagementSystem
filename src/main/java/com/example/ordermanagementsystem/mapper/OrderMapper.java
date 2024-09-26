package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dto.OrderDTO;
import com.example.ordermanagementsystem.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    Order orderDTOToOrder(OrderDTO orderDTO);
}
