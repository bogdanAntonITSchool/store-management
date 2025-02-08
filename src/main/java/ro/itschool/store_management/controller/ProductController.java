package ro.itschool.store_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.store_management.dto.ProductDto;
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
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/name")
    public List<ProductDto> getProductsByName(@RequestParam String name) {
        return productService.getProductsByName(name);
    }

    @GetMapping("/name/category")
    public List<ProductDto> getProductsByNameAndCategory(@RequestParam String name,
                                                         @RequestParam String category) {
        return productService.getProductsByNameAndCategory(name, category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id) {
        ProductDto productDtoById = productService.getProductById(id);

        if (productDtoById == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productDtoById);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Product added successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable long id,
                                                    @RequestBody ProductDto productDto) {
        ProductDto existingProductDto = productService.getProductById(id);

        if (existingProductDto == null) {
            return ResponseEntity.notFound().build();
        }

        productService.updateProduct(productDto, existingProductDto);

        return ResponseEntity.ok(existingProductDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable long id,
                                                     @RequestBody ProductDto productDto) {
        ProductDto existingProductDto = productService.getProductById(id);

        if (existingProductDto == null) {
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

        productService.replaceProduct(productDto, existingProductDto);

        return ResponseEntity.ok(existingProductDto);
    }

}
