package ro.itschool.store_management.service;

import org.springframework.stereotype.Service;
import ro.itschool.store_management.dto.ProductDto;
import ro.itschool.store_management.mapper.ObjectMapper;
import ro.itschool.store_management.persistence.entity.Product;
import ro.itschool.store_management.persistence.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    // We inject the ProductRepository and the ObjectMapper in the constructor.
    private final ProductRepository productRepository;
    // Observe that we use the ObjectMapper interface to declare the productMapper field.
    private final ObjectMapper<ProductDto, Product> productMapper;

    public ProductService(ProductRepository productRepository,
                          ObjectMapper<ProductDto, Product> productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getProductsByName(String name) {
        List<Product> productsByName = productRepository.getProductsByName(name);

        return productsByName.stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    public List<ProductDto> getProductsByNameAndCategory(String name, String category) {
//        List<Product> products = productRepository.findProductsByNameAndCategoryJpql(name, category);
        List<Product> products = productRepository.findProductsByNameAndCategoryNative(name, category);

        return products.stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    public List<ProductDto> getAllProducts() {
        // SELECT * FROM product
        List<Product> allProducts = productRepository.findAll();

        return allProducts.stream()
                .map(productMapper::mapToDto)
                .toList();
    }

    public ProductDto getProductById(long id) {
        // SELECT * FROM product WHERE id = ?
        Product referenceById = productRepository.getReferenceById(id);

        return productMapper.mapToDto(referenceById);
    }

    public void addProduct(ProductDto productDto) {
        Product product = productMapper.mapToEntity(productDto);

        // INSERT INTO product (name, category, price, quantity)
        // VALUES (?, ?, ?, ?)
        productRepository.save(product);
    }

    public void deleteProductById(long id) {
        // DELETE FROM product WHERE id = ?
        productRepository.deleteById(id);
    }

    // TODO: Still in progress
    public void updateProduct(ProductDto productDto, ProductDto existingProductDto) {
        if (productDto.getName() != null) {
            existingProductDto.setName(productDto.getName());
        }

        if (productDto.getCategory() != null) {
            existingProductDto.setCategory(productDto.getCategory());
        }

        if (productDto.getPrice() != 0) {
            existingProductDto.setPrice(productDto.getPrice());
        }

        if (productDto.getQuantity() != 0) {
            existingProductDto.setQuantity(productDto.getQuantity());
        }
    }

    // TODO: Still in progress
    public void replaceProduct(ProductDto productDto, ProductDto existingProductDto) {
        existingProductDto.setName(productDto.getName());
        existingProductDto.setCategory(productDto.getCategory());
        existingProductDto.setPrice(productDto.getPrice());
        existingProductDto.setQuantity(productDto.getQuantity());
    }

}
