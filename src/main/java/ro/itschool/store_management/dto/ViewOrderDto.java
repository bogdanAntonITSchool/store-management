package ro.itschool.store_management.dto;

import java.util.Set;

/**
 * This is a DTO class that is used to represent the view of an order.
 * We display all details about a client in this DTO.
 * We display all details about the products list in this DTO.
 *
 * @param id
 * @param totalPrice
 * @param status
 * @param clientId
 * @param products
 */
public record ViewOrderDto(
        Long id,
        double totalPrice,
        String status,
        ClientDto clientId,
        Set<ProductDto> products
) {
}
