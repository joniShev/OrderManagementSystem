package com.example.ordermanagementsystem.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private String description;
    private Double totalPrice;
}
