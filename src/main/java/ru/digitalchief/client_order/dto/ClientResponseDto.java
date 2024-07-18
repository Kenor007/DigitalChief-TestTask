package ru.digitalchief.client_order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ClientResponseDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private Set<OrderResponseDto> orders;
}