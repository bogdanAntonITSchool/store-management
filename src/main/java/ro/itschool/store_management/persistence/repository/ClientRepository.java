package ro.itschool.store_management.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.itschool.store_management.persistence.entity.Client;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // SELECT c FROM Client c WHERE c.address.city = :city
    // This query will return all clients that have an address in the specified city.
    // The query is written in JPQL (Java Persistence Query Language).
    // JPQL is a platform-independent query language defined as part of the JPA specification.
    @Query("SELECT c FROM Client c WHERE c.address.city = :city")
    List<Client> findByCity(String city);

    // SELECT c.id, c.name, c.address_id FROM client c INNER JOIN address a ON c.address_id = a.id WHERE a.city = :city
    // This query will return the id, name and address_id of all clients that have an address in the specified city.
    // The query is written in native SQL.
    // Native SQL queries are database-specific queries that are written in the native query language of the database.

    // id -> client
    // nume -> client
    // id -> address
    // city -> address
    // street -> address
    // number -> address
    @Query(value =
            "SELECT c.id, c.name, c.address_id " +
            "FROM client c " +
            "INNER JOIN address a ON c.address_id = a.id " +
            "WHERE a.city = :city",
            nativeQuery = true)
    List<Client> findByCityNative(String city);
}
