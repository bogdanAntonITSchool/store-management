package ro.itschool.store_management.service;

import org.springframework.stereotype.Service;
import ro.itschool.store_management.persistence.entity.Order;
import ro.itschool.store_management.persistence.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}
