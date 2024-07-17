package ru.digitalchief.client_order.service;

import ru.digitalchief.client_order.dto.ClientRequestDto;
import ru.digitalchief.client_order.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {
    ClientResponseDto createClient(ClientRequestDto clientRequestDto);

    ClientResponseDto findClientById(Long clientId);

    List<ClientResponseDto> findAllClients();

    ClientResponseDto updateClientById(Long clientId, ClientRequestDto clientRequestDto);

    void deleteClientById(Long clientId);
}