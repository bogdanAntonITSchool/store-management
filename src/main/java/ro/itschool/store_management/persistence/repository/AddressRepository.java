package ro.itschool.store_management.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.itschool.store_management.persistence.entity.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // SELECT a FROM Address a WHERE a.city = :city
    // This query will return all addresses that have the specified city.
    // The query is written in JPQL (Java Persistence Query Language).
    @Query("SELECT a FROM Address a WHERE a.city = :city")
    List<Address> findByCity(String city);

}
