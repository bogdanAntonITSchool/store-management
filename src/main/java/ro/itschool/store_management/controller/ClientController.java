package ro.itschool.store_management.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.store_management.dto.ClientDto;
import ro.itschool.store_management.service.ClientService;
import ro.itschool.store_management.validator.ClientValidator;

import java.util.List;


// This controller will handle two entities: Client and Address.
// We don't expose any endpoint for the Address entity, but we need to map the Address entity to the AddressDto in the ClientDto.
// It does not make any sense to create standalone addresses without clients.
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientValidator clientValidator;

    public ClientController(ClientService clientService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.clientValidator = clientValidator;
    }

    // RequestBody annotation is used to bind the request body to a method parameter.
    // In our case ClientDto is the request body that will be mapped to the client parameter.
    // ClientDto contains an AddressDto object that will be mapped to the Address entity.

    // An example of a request body that can be sent to this endpoint:
    // {
    //     "name": "Bogdan",
    //     "address": {
    //         "city": "Cluj-Napoca",
    //         "street": "Observatorului",
    //         "number": 1
    //     }
    // }
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto client) {
        clientValidator.validateClient(client);

        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    // PathVariable annotation is used to bind the URI template variable to a method parameter.
    // In our case, the city parameter will be mapped to the {city} variable in the URI.
    @GetMapping("/city/{city}")
    public ResponseEntity<List<ClientDto>> getClientsByCity(@PathVariable String city) {
        return ResponseEntity.ok(clientService.getClientsByCity(city));
    }

}
