package ro.itschool.store_management.service;

import org.springframework.stereotype.Service;
import ro.itschool.store_management.dto.ClientDto;
import ro.itschool.store_management.mapper.ObjectMapper;
import ro.itschool.store_management.persistence.entity.Client;
import ro.itschool.store_management.persistence.repository.ClientRepository;

import java.util.List;

// Nothing too fancy here, just a service that uses the ClientRepository and the ObjectMapper to create and retrieve clients.
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ObjectMapper<ClientDto, Client> clientMapper;

    public ClientService(ClientRepository clientRepository,
                         ObjectMapper<ClientDto, Client> clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDto createClient(ClientDto client) {
        Client entity = clientMapper.mapToEntity(client);

        return clientMapper.mapToDto(clientRepository.save(entity));
    }

    public List<ClientDto> getClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::mapToDto)
                .toList();
    }

}
