package ro.itschool.store_management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
// The @Table annotation is used to specify the name of the table that is used to store the entity.
// In PostgreSQL, there is a reserved keyword called "order", so we need to specify a different name for the table.
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float totalPrice;
    private String status;

    @JsonBackReference

    // The @ManyToOne annotation is used to specify that the order entity has a many-to-one relationship with the client entity.
    // The fetch attribute is used to specify the fetch type of the relationship.
    @ManyToOne(fetch = FetchType.LAZY)

    // The @JoinColumn annotation is used to specify the column that is used to join the order and client entities.
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    // The @ManyToMany annotation is used to specify that the order entity has a many-to-many relationship with the product entity.
    // The mappedBy attribute is used to specify the name of the field in the product entity that is used to join the entities.
    @ManyToMany(mappedBy = "orders")
    private Set<Product> products;

}
