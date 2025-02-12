package ro.itschool.store_management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String street;
    private String number;

    // The @JsonManagedReference annotation is used to specify that the address entity is the owner of the relationship with the client entity.
    @JsonManagedReference

    // The @OneToOne annotation is used to specify that the address entity has a one-to-one relationship with the client entity.
    // The mappedBy attribute is used to specify the name of the field in the client entity that is used to join the entities.
    @OneToOne(mappedBy = "address")
    private Client client;

}
