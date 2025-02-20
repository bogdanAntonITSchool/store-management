package ro.itschool.store_management.validator;

import org.springframework.stereotype.Component;
import ro.itschool.store_management.dto.ClientDto;
import ro.itschool.store_management.exception.InvalidPayloadException;


@Component
public class ClientValidator {

    public void validateClient(ClientDto client) {
        if (client == null) {
            throw new InvalidPayloadException("Client cannot be null");
        }

        if (client.getName() == null || client.getName().isEmpty()) {
            throw new InvalidPayloadException("Client name cannot be null or empty");
        }
    }

}
