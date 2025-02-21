package ro.itschool.store_management.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.store_management.dto.OrderDto;
import ro.itschool.store_management.dto.ProductDto;
import ro.itschool.store_management.mapper.ObjectMapper;
import ro.itschool.store_management.persistence.entity.Order;
import ro.itschool.store_management.persistence.entity.Product;
import ro.itschool.store_management.persistence.repository.ClientRepository;
import ro.itschool.store_management.persistence.repository.ProductRepository;

import java.util.HashSet;
import java.util.Set;


@Component
public class OrderMapper implements ObjectMapper<OrderDto, Order> {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper<ProductDto, Product> productMapper;

    public OrderMapper(ClientRepository clientRepository,
                       ProductRepository productRepository,
                       ObjectMapper<ProductDto, Product> productMapper) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * We don't need this method because we don't need to map an Order to an OrderDto.
     * When we display back the order to the user, we use the ViewOrderMapper.
     *
     * @param order
     * @return
     */
    @Override
    public OrderDto mapToDto(Order order) {
        // not used
        return null;
    }

    /**
     * This method is used to map an OrderDto to an Order.
     * When we create a new order, we use this method to map the OrderDto to an Order.
     *
     * @param orderDto
     * @return
     */
    @Override
    public Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();

        order.setStatus(orderDto.status());

        clientRepository.findById(orderDto.clientId())
                .ifPresent(order::setClient);

        Set<Product> products = new HashSet<>();
        orderDto.products().forEach(
                productId ->
                        productRepository.findById(productId)
                                .ifPresent(product -> {
                                    Set<Order> orders = product.getOrders();
                                    orders.add(order);

                                    products.add(product);
                                })
        );

        order.setProducts(products);

        return order;
    }

    private static double computeTotalPrice(Set<ProductDto> products) {
        return products.stream()
                .map(ProductDto::getPrice)
                .reduce(0d, Double::sum);
    }

}
