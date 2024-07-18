package ru.digitalchief.client_order.dto;

import lombok.Getter;
import lombok.Setter;
import ru.digitalchief.client_order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
}