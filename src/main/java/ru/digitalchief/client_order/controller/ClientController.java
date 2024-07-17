package ru.digitalchief.client_order.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.digitalchief.client_order.dto.ClientRequestDto;
import ru.digitalchief.client_order.dto.ClientResponseDto;
import ru.digitalchief.client_order.service.ClientService;

import java.util.List;

import static ru.digitalchief.client_order.error_handling.constant.ExceptionAnswer.POSITIVE_ID;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDto createClient(@Valid @RequestBody ClientRequestDto clientRequestDto) {
        log.debug("Creating client: {}", clientRequestDto);
        return clientService.createClient(clientRequestDto);
    }

    @GetMapping("/{id}")
    public ClientResponseDto findClientById(@PathVariable("id") @Positive(message = POSITIVE_ID) Long id) {
        log.debug("Getting client by id: {}", id);
        return clientService.findClientById(id);
    }

    @GetMapping()
    public List<ClientResponseDto> findAllClients() {
        log.debug("Getting all clients");
        return clientService.findAllClients();
    }

    @PutMapping("/{id}")
    public ClientResponseDto updateClientById(
            @PathVariable("id") @Positive(message = POSITIVE_ID) Long id,
            @Valid @RequestBody ClientRequestDto clientRequestDto) {
        log.debug("Updating client {} by id {}", clientRequestDto, id);
        return clientService.updateClientById(id, clientRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable("id") @Positive(message = POSITIVE_ID) Long id) {
        log.debug("Deleting client by id {}", id);
        clientService.deleteClientById(id);
    }
}