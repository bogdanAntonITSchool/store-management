package ro.itschool.store_management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


// NOTE! that we can have empty lines between the annotations and the fields.

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // @JsonBackReference and @JsonManagedReference are used to prevent infinite recursion
    // when serializing the entities to JSON

    // If you don't use these annotations, you will get a StackOverflowError
    // This is caused by the fact that the client and address entities reference each other leading to an infinite loop.
    @JsonBackReference

    // The @OneToOne annotation is used to specify that the client entity has a one-to-one relationship with the address entity.
    // The cascade attribute is used to specify that all operations (PERSIST, REMOVE, REFRESH, MERGE, DETACH) should be cascaded to the address entity.
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)

    // The @JoinColumn annotation is used to specify the column that is used to join the client and address entities.
    // The name attribute is used to specify the name of the column in the client entity that is used to join the entities.
    // The referencedColumnName attribute is used to specify the name of the column in the address entity that is used to join the entities.
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    // The @JsonManagedReference annotation is used to specify that the client entity is the owner of the relationship with the order entity.
    // This means that the client entity is responsible for managing the relationship with the order entity.
    @JsonManagedReference

    // The @OneToMany annotation is used to specify that the client entity has a one-to-many relationship with the order entity.
    // The mappedBy attribute is used to specify the name of the field in the order entity that is used to join the entities.
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

}
