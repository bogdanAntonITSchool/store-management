package ro.itschool.store_management.mapper.impl;

import org.springframework.stereotype.Component;
import ro.itschool.store_management.dto.ClientDto;
import ro.itschool.store_management.dto.ProductDto;
import ro.itschool.store_management.dto.ViewOrderDto;
import ro.itschool.store_management.mapper.ObjectMapper;
import ro.itschool.store_management.persistence.entity.Client;
import ro.itschool.store_management.persistence.entity.Order;
import ro.itschool.store_management.persistence.entity.Product;

import java.util.stream.Collectors;


@Component
public class ViewOrderMapper implements ObjectMapper<ViewOrderDto, Order> {

    private final ObjectMapper<ClientDto, Client> clientMapper;
    private final ObjectMapper<ProductDto, Product> productMapper;

    public ViewOrderMapper(ObjectMapper<ClientDto, Client> clientMapper,
                           ObjectMapper<ProductDto, Product> productMapper) {
        this.clientMapper = clientMapper;
        this.productMapper = productMapper;
    }

    /**
     * When we want to get data about an order we'll use this method to map the Order to a ViewOrderDto.
     *
     * @param order
     * @return
     */
    @Override
    public ViewOrderDto mapToDto(Order order) {
        return new ViewOrderDto(
                order.getId(),
                order.getTotalPrice(),
                order.getStatus(),
                clientMapper.mapToDto(order.getClient()),
                order.getProducts().stream()
                        .map(productMapper::mapToDto)
                        .collect(Collectors.toSet())
        );
    }

    /**
     * We don't need this method because we don't need to map a ViewOrderDto to an Order.
     * When we create a new order, we'll use an OrderDto and the OrderMapper.
     *
     * @param orderDto
     * @return
     */
    @Override
    public Order mapToEntity(ViewOrderDto orderDto) {
        // not used
        return null;
    }

}
