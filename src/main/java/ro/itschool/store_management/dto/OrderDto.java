package ro.itschool.store_management.dto;

import java.util.Set;

/**
 * A record class is a special type of class introduced in Java 14. It is a special type of class that is introduced in Java 14.
 * It is a compact class that is used to store immutable data.
 * It is a final class that cannot be extended.
 * <p>
 * We can have multiple DTOs for the same entity, each DTO can have different fields.
 *
 * @param id
 * @param totalPrice
 * @param status
 * @param clientId
 * @param products
 */
public record OrderDto(
        Long id,
        double totalPrice,
        String status,
        Long clientId,
        Set<Long> products
) {
}
