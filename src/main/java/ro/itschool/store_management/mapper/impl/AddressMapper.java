package ro.itschool.store_management.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.store_management.dto.ClientDto;
import ro.itschool.store_management.mapper.ObjectMapper;
import ro.itschool.store_management.persistence.entity.Address;

@Component
public class AddressMapper implements ObjectMapper<ClientDto.AddressDto, Address> {

    @Override
    public ClientDto.AddressDto mapToDto(Address address) {
        return new ClientDto.AddressDto(
                address.getCity(),
                address.getStreet(),
                address.getNumber()
        );
    }

    @Override
    public Address mapToEntity(ClientDto.AddressDto addressDto) {
        Address address = new Address();

        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());

        return address;
    }

}
