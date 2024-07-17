package ru.digitalchief.client_order.dto;

import ru.digitalchief.client_order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponseDto {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
}