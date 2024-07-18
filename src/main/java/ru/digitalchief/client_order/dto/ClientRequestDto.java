package ru.digitalchief.client_order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ClientRequestDto {
    @NotBlank(message = "Client name should not be blank")
    @Size(min = 1, message = "Client name should contain at least 1 character")
    private String name;

    @NotBlank(message = "Phone number should not be blank")
    @Size(min = 1, message = "Phone number should contain at least 1 character")
    private String phoneNumber;

    @NotBlank(message = "Address should not be blank")
    @Size(min = 1, message = "Address should contain at least 1 character")
    private String address;

    @NotBlank(message = "Email should not be blank")
    @Size(min = 1, message = "Email should contain at least 1 character")
    @Email(message = "Email must have the format of an email address")
    private String email;

    @Valid
    private Set<OrderRequestDto> orders;
}