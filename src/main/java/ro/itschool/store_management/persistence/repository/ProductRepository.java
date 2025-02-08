package ro.itschool.store_management.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.itschool.store_management.persistence.entity.Product;

import java.util.List;


// This is an interface that extends JpaRepository.
// JpaRepository is an interface that provides methods for CRUD operations.
// We can use these methods to interact with the database.
// The generics <Product, Long> specify that we are working with the Product entity and the id field is of type Long.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // This method follows the naming convention of Spring Data JPA and it will generate the SQL query automatically.
    // based on naming convention
    List<Product> getProductsByName(String name);

    // This method uses JPQL (Java Persistence Query Language) to select products by name and category.
    // We use the @Query annotation to specify the JPQL query.
    // The '?1' and '?2' are placeholders for the parameters.
    // We can use named parameters like ':name' and ':category' instead of '?1' and '?2'.
    // When an annotation has a value attribute, we can omit the attribute name.
    // JPQL select by name
    @Query("SELECT p FROM Product p WHERE p.name = ?1 and p.category = ?2")
    List<Product> findProductsByNameAndCategoryJpql(String name, String category);

    // This method uses native SQL to select products by name and category.
    // We use the @Query annotation to specify the native SQL query.
    // The '?1' and '?2' are placeholders for the parameters.
    // We can use named parameters like ':name' and ':category' instead of '?1' and '?2'.
    // When an annotation has a value attribute, but we use more attributes, we must specify the attribute name (including 'value').
    // Native SQL
    @Query(value = "SELECT * FROM product WHERE product_name = ?1 and category = ?2", nativeQuery = true)
    List<Product> findProductsByNameAndCategoryNative(String name, String category);

}
