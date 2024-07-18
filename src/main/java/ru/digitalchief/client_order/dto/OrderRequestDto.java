package ru.digitalchief.client_order.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import ru.digitalchief.client_order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderRequestDto {
    @NotBlank(message = "Order description should not be blank")
    @Size(min = 1, message = "Order description should contain at least 1 character")
    private String description;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount cannot be less than 0.01")
    @Digits(integer = 10, fraction = 2, message = "Amount must have an accuracy of 2 digits")
    private BigDecimal amount;

    @NotNull(message = "Order date is required")
    @FutureOrPresent(message = "Order date must be in the present or future")
    private LocalDateTime orderDate;

    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus;
}