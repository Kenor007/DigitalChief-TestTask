package ru.digitalchief.client_order.dto;

import java.util.Set;

public class ClientResponseDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private Set<OrderResponseDto> orders;
}