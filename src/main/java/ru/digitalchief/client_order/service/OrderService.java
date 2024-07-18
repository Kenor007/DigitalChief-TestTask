package ru.digitalchief.client_order.service;

import ru.digitalchief.client_order.dto.OrderRequestDto;
import ru.digitalchief.client_order.dto.OrderResponseDto;
import ru.digitalchief.client_order.model.Client;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto findOrderById(Long orderId);

    List<OrderResponseDto> findAllOrders();

    OrderResponseDto updateOrderById(Long orderId, OrderRequestDto orderRequestDto);

    void deleteOrderById(Long orderId);

    void updateOrdersWithClient(Client client);
}