package ro.itschool.store_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.store_management.model.Product;
import ro.itschool.store_management.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product productById = productService.getProductById(id);

        if (productById == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productById);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Product added successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,
                                                 @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct == null) {
            return ResponseEntity.notFound().build();
        }

        productService.updateProduct(product, existingProduct);

        return ResponseEntity.ok(existingProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable long id,
                                                  @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);

        if (existingProduct == null) {
            return ResponseEntity.notFound().build();

            // or we can create a new product with the same id if it doesn't exist
            // existingProduct = new Product(
            // product.getId(),
            // product.getName(),
            // product.getCategory(),
            // product.getPrice(),
            // product.getQuantity()
            // );
        }

        productService.replaceProduct(product, existingProduct);

        return ResponseEntity.ok(existingProduct);
    }

}
