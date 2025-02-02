package ro.itschool.store_management.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.itschool.store_management.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final List<Product> PRODUCTS = new ArrayList<>();

    public List<Product> getAllProducts() {
        return PRODUCTS;
    }

    public Product getProductById(long id) {
        return PRODUCTS.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        PRODUCTS.add(product);
    }

    public void deleteProductById(long id) {
        PRODUCTS.removeIf(p -> p.getId() == id);
    }

    public void updateProduct(Product product, Product existingProduct) {
        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }

        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }

        if (product.getPrice() != 0) {
            existingProduct.setPrice(product.getPrice());
        }

        if (product.getQuantity() != 0) {
            existingProduct.setQuantity(product.getQuantity());
        }
    }

    public void replaceProduct(Product product, Product existingProduct) {
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
    }

}
