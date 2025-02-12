package ro.itschool.store_management.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.store_management.dto.ClientDto;
import ro.itschool.store_management.mapper.ObjectMapper;
import ro.itschool.store_management.persistence.entity.Address;
import ro.itschool.store_management.persistence.entity.Client;

@Component
public class ClientMapper implements ObjectMapper<ClientDto, Client> {

    private final ObjectMapper<ClientDto.AddressDto, Address> addressMapper;

    public ClientMapper(ObjectMapper<ClientDto.AddressDto, Address> addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public ClientDto mapToDto(Client client) {
        return new ClientDto(
                client.getId(),
                client.getName(),

                // We need to map the Address entity to the AddressDto
                addressMapper.mapToDto(client.getAddress())
        );
    }

    @Override
    public Client mapToEntity(ClientDto clientDto) {
        Client client = new Client();

        client.setName(clientDto.getName());

        // We need to map the AddressDto to the Address entity
        client.setAddress(addressMapper.mapToEntity(clientDto.getAddress()));

        return client;
    }

}
