package ru.digitalchief.client_order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalchief.client_order.dto.OrderRequestDto;
import ru.digitalchief.client_order.dto.OrderResponseDto;
import ru.digitalchief.client_order.error_handling.exception.OrderExistsException;
import ru.digitalchief.client_order.error_handling.exception.OrderNotFoundException;
import ru.digitalchief.client_order.mapper.OrderMapper;
import ru.digitalchief.client_order.model.Order;
import ru.digitalchief.client_order.repository.OrderRepository;
import ru.digitalchief.client_order.service.OrderService;

import java.util.List;

import static ru.digitalchief.client_order.error_handling.constant.ExceptionAnswer.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.orderRequestDtoToOrder(orderRequestDto);
        validateOrder(order);
        Order savedOrder = orderRepository.save(order);
        log.debug("Order with id {} is saved", savedOrder.getId());
        return orderMapper.orderToOrderResponseDto(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto findOrderById(Long orderId) {
        Order order = findOrderByIdOrThrow(orderId);
        log.debug("Order with id {} is found", order.getId());
        return orderMapper.orderToOrderResponseDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            log.debug("List of orders is empty");
        }
        log.debug("List of orders has been found");
        return orders.stream().map(orderMapper::orderToOrderResponseDto).toList();
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrderById(Long orderId, OrderRequestDto orderRequestDto) {
        Order order = findOrderByIdOrThrow(orderId);
        log.debug("Updating order {}", order);
        order = orderMapper.orderRequestDtoToUpdatedOrder(orderRequestDto, order);
        order = orderRepository.save(order);
        log.debug("Order with id {} is updated", order.getId());
        return orderMapper.orderToOrderResponseDto(order);
    }

    @Override
    @Transactional
    public void deleteOrderById(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            Order deletedOrder = findOrderByIdOrThrow(orderId);
            orderRepository.deleteById(orderId);
            log.debug("Order with id {} is deleted", orderId);
        } else {
            log.error("Order with id {} not found", orderId);
            throw new OrderNotFoundException(String.format(ORDER_NOT_FOUND, orderId));
        }
    }

    private void validateOrder(Order order) {
        log.debug("Checking order {}", order);
        if (orderRepository.findOrder(order).isPresent()) {
            log.error("Order already exists");
            throw new OrderExistsException(ORDER_ALREADY_EXISTS);
        }
    }

    private Order findOrderByIdOrThrow(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> {
            log.error("Order with id {} not found", orderId);
            return new OrderNotFoundException(String.format(ORDER_NOT_FOUND, orderId));
        });
    }
}