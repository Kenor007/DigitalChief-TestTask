package ru.digitalchief.client_order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Client Controller", description = "API for working with clients")
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @Operation(summary = "Create a client by params",
            description = "Returns information about the client, if the client has been created")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDto createClient(@Valid @RequestBody ClientRequestDto clientRequestDto) {
        log.debug("Creating client: {}", clientRequestDto);
        return clientService.createClient(clientRequestDto);
    }

    @Operation(summary = "Get information about the client by id",
            description = "Returns an information about the client as per the id")
    @GetMapping("/{id}")
    public ClientResponseDto findClientById(@PathVariable("id") @Positive(message = POSITIVE_ID) Long id) {
        log.debug("Getting client by id: {}", id);
        return clientService.findClientById(id);
    }

    @Operation(summary = "Get a list of all clients with their information",
            description = "Returns a list of all clients with their information")
    @GetMapping()
    public List<ClientResponseDto> findAllClients() {
        log.debug("Getting all clients");
        return clientService.findAllClients();
    }

    @Operation(summary = "Update client by id",
            description = "Returns an information about the updated client as per the id")
    @PutMapping("/{id}")
    public ClientResponseDto updateClientById(
            @PathVariable("id") @Positive(message = POSITIVE_ID) Long id,
            @Valid @RequestBody ClientRequestDto clientRequestDto) {
        log.debug("Updating client {} by id {}", clientRequestDto, id);
        return clientService.updateClientById(id, clientRequestDto);
    }

    @Operation(summary = "Delete client by id",
            description = "Returns NO CONTENT if client has been deleted by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable("id") @Positive(message = POSITIVE_ID) Long id) {
        log.debug("Deleting client by id {}", id);
        clientService.deleteClientById(id);
    }
}