package ro.itschool.store_management.mapper;

/**
 * This is an interface that is used to map DTOs to Entities and vice versa.
 *
 * @param <T> is the DTO class
 * @param <R> is the Entity class
 */
public interface ObjectMapper<T, R> {

    T mapToDto(R r);

    R mapToEntity(T t);

}
