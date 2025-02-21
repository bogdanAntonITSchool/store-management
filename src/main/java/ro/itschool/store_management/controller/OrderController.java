package ro.itschool.store_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.store_management.dto.OrderDto;
import ro.itschool.store_management.dto.ViewOrderDto;
import ro.itschool.store_management.mapper.ObjectMapper;
import ro.itschool.store_management.persistence.entity.Order;
import ro.itschool.store_management.service.OrderService;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final ObjectMapper<OrderDto, Order> orderMapper;
    private final ObjectMapper<ViewOrderDto, Order> viewOrderMapper;

    public OrderController(OrderService orderService,
                           ObjectMapper<OrderDto, Order> orderMapper,
                           ObjectMapper<ViewOrderDto, Order> viewOrderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.viewOrderMapper = viewOrderMapper;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) {
        Order mappedOrder = orderMapper.mapToEntity(order);
        Order createdOrder = orderService.createOrder(mappedOrder);

        return new ResponseEntity<>(orderMapper.mapToDto(createdOrder), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ViewOrderDto>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders().stream()
                .map(viewOrderMapper::mapToDto)
                .toList());
    }

}
