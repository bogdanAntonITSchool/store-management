package ro.itschool.store_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * This is a class that is used to represent a ProductDto object.
 * A DTO (Data Transfer Object) is an object that is used to transfer data between different layers of an application.
 * The ProductDto object is used to represent a product in the store.
 * We do not expose directly the Product object because it can contain sensitive information.
 */
@Setter
@Getter
@AllArgsConstructor
public class ProductDto {

    private final long id;

    private String name;
    private String category;
    private double price;
    private int quantity;

}
