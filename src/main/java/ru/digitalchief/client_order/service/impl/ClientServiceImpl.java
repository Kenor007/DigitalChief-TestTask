package ru.digitalchief.client_order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalchief.client_order.dto.ClientRequestDto;
import ru.digitalchief.client_order.dto.ClientResponseDto;
import ru.digitalchief.client_order.error_handling.exception.ClientExistsException;
import ru.digitalchief.client_order.error_handling.exception.ClientNotFoundException;
import ru.digitalchief.client_order.mapper.ClientMapper;
import ru.digitalchief.client_order.model.Client;
import ru.digitalchief.client_order.repository.ClientRepository;
import ru.digitalchief.client_order.service.ClientService;

import java.util.List;

import static ru.digitalchief.client_order.error_handling.constant.ExceptionAnswer.CLIENT_ALREADY_EXISTS;
import static ru.digitalchief.client_order.error_handling.constant.ExceptionAnswer.CLIENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponseDto createClient(ClientRequestDto clientRequestDto) {
        Client client = clientMapper.clientRequestDtoToClient(clientRequestDto);
        validateClient(client);
        Client savedClient = clientRepository.save(client);
        log.debug("Client with id {} is saved", savedClient.getId());
        return clientMapper.clientToClientResponseDto(savedClient);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDto findClientById(Long clientId) {
        Client client = findClientByIdOrThrow(clientId);
        log.debug("Client with id {} is found", client.getId());
        return clientMapper.clientToClientResponseDto(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            log.debug("List of clients is empty");
        }
        log.debug("List of clients has been found");
        return clients.stream().map(clientMapper::clientToClientResponseDto).toList();
    }

    @Override
    @Transactional
    public ClientResponseDto updateClientById(Long clientId, ClientRequestDto clientRequestDto) {
        Client client = findClientByIdOrThrow(clientId);
        log.debug("Updating client {}", client);
        client = clientMapper.clientRequestDtoToUpdatedClient(clientRequestDto, client);
        client = clientRepository.save(client);
        log.debug("Client with id {} is updated", client.getId());
        return clientMapper.clientToClientResponseDto(client);
    }

    @Override
    @Transactional
    public void deleteClientById(Long clientId) {
        if (clientRepository.existsById(clientId)) {
            Client deletedClient = findClientByIdOrThrow(clientId);
            clientRepository.deleteById(clientId);
            log.debug("Client with id {} is deleted", clientId);
        } else {
            log.error("Client with id {} not found", clientId);
            throw new ClientNotFoundException(String.format(CLIENT_NOT_FOUND, clientId));
        }
    }

    private void validateClient(Client client) {
        log.debug("Checking client {}", client);
        if (clientRepository.findClient(client).isPresent()) {
            log.error("Client already exists");
            throw new ClientExistsException(CLIENT_ALREADY_EXISTS);
        }
    }

    private Client findClientByIdOrThrow(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> {
            log.error("Client with id {} not found", clientId);
            return new ClientNotFoundException(String.format(CLIENT_NOT_FOUND, clientId));
        });
    }
}