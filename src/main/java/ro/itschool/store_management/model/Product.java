package ro.itschool.store_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Product {

    private final long id;

    private String name;
    private String category;
    private double price;
    private int quantity;

}
