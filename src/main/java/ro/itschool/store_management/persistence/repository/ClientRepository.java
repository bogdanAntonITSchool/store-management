package ro.itschool.store_management.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.store_management.persistence.entity.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
