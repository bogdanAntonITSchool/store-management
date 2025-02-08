package ro.itschool.store_management.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


// The @Entity annotation is used to mark the class as an entity.
// An entity is a lightweight persistence domain object.
// Typically, an entity represents a table in a relational database.
@Entity
@Getter
@Setter
public class Product {

    // @Id annotation marks a field within an entity as the primary key field.

    // @GeneratedValue annotation provides for the specification of generation strategies for the values of primary keys.
    // The IDENTITY strategy is used to generate a value for the primary key column using an identity column in the database.
    // The identity column is typically defined in the database as an auto-increment column.
    // Nowadays, the UUID strategy is preferred because it is more secure.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column annotation is used to specify the details of the column to which a field or property will be mapped.
    // You can set the name, nullable, unique, length, etc. of the column.
    @Column(nullable = false, name = "product_name")
    private String name;

    // The rest of the fields are not annotated because they will be mapped to columns with the same name as the field.
    // And the data type of the field will be determined by the data type of the column in the database.
    private String category;
    private double price;

    // @Transient annotation is used to indicate that a field is not to be persisted in the database.
    private int quantity;

}
