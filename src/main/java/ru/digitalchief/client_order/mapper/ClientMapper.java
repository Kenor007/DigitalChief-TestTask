package ru.digitalchief.client_order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.digitalchief.client_order.dto.ClientRequestDto;
import ru.digitalchief.client_order.dto.ClientResponseDto;
import ru.digitalchief.client_order.model.Client;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    Client clientRequestDtoToClient(ClientRequestDto clientRequestDto);

    ClientResponseDto clientToClientResponseDto(Client client);

    Client clientRequestDtoToUpdatedClient(ClientRequestDto clientRequestDto, @MappingTarget Client client);
}