package ru.digitalchief.client_order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.digitalchief.client_order.dto.OrderRequestDto;
import ru.digitalchief.client_order.dto.OrderResponseDto;
import ru.digitalchief.client_order.model.Order;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order orderRequestDtoToOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto orderToOrderResponseDto(Order order);

    Order orderRequestDtoToUpdatedOrder(OrderRequestDto orderRequestDto, @MappingTarget Order order);
}