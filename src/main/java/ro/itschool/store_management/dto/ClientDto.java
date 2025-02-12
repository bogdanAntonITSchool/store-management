package ro.itschool.store_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientDto {

    private Long id;
    private String name;
    private AddressDto address;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class AddressDto {

        private String city;
        private String street;
        private String number;

    }

}
