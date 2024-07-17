package ru.digitalchief.client_order.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.digitalchief.client_order.dto.OrderRequestDto;
import ru.digitalchief.client_order.dto.OrderResponseDto;
import ru.digitalchief.client_order.service.OrderService;

import java.util.List;

import static ru.digitalchief.client_order.error_handling.constant.ExceptionAnswer.POSITIVE_ID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        log.debug("Creating order: {}", orderRequestDto);
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/{id}")
    public OrderResponseDto findOrderById(@PathVariable("id") @Positive(message = POSITIVE_ID) Long id) {
        log.debug("Getting order by id: {}", id);
        return orderService.findOrderById(id);
    }

    @GetMapping()
    public List<OrderResponseDto> findAllOrders() {
        log.debug("Getting all orders");
        return orderService.findAllOrders();
    }

    @PutMapping("/{id}")
    public OrderResponseDto updateOrderById(
            @PathVariable("id") @Positive(message = POSITIVE_ID) Long id,
            @Valid @RequestBody OrderRequestDto orderRequestDto) {
        log.debug("Updating order {} by id {}", orderRequestDto, id);
        return orderService.updateOrderById(id, orderRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable("id") @Positive(message = POSITIVE_ID) Long id) {
        log.debug("Deleting order by id {}", id);
        orderService.deleteOrderById(id);
    }
}