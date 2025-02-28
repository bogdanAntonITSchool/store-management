package ro.itschool.store_management.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.itschool.store_management.persistence.entity.Order;
import ro.itschool.store_management.persistence.repository.OrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

// MockitoExtension is used to
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    // By using MockitoExtension, we can use the @Mock and @InjectMocks annotations to create mock objects and inject them into the test class.
    @Mock
    private OrderRepository orderRepository;

    // @InjectMocks annotation is used to inject the mock objects into the test class.
    @InjectMocks
    private OrderService orderService;

    // @BeforeEach annotation is used to tell JUnit to run the setUp method before each test method.
    // This is an alternative if we want to avoid using the MockitoExtension.
//    @BeforeEach
//    void setUp() {
//        orderRepository = mock(OrderRepository.class);
//        orderService = new OrderService(orderRepository);
//    }

    // @Test annotation is used to tell JUnit that the following method is a test method.
    // This method can be executed like a main method.
    @Test
    // @DisplayName annotation is used to provide a custom name for the test method.
    @DisplayName("Test create order")
    void testCreateOrder() {
        // given
        Order order = new Order();

        Order savedOrder = new Order();

        savedOrder.setId(1L);

        // when
        // when method is used to define the behavior of the mock object.
        // In this case, we are telling the mock object to return the savedOrder object when the save method is called with the order object.
        when(orderRepository.save(order)).thenReturn(savedOrder);

        // then
        Order result = orderService.createOrder(order);

        // assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        // assertSame checks that the savedOrder object is the same as the result object. (same reference)
        assertSame(savedOrder, result);

        // verify checks that the save method was called on the orderRepository object.
        verify(orderRepository).save(order);
        // verifyNoMoreInteractions checks that no other methods were called on the orderRepository object.
        verifyNoMoreInteractions(orderRepository);
    }

    @Test
    void testCreateOrder_whenExceptionThrown_thenDoNothing() {
        // given
        Order order = new Order();

        // when
        when(orderRepository.save(order)).thenThrow(new IllegalArgumentException());

        // then
        assertThrows(IllegalArgumentException.class, () -> orderService.createOrder(order));
    }

    @Test
    void testGetOrders() {
        // given
        Order order = new Order();

        order.setId(1L);

        List<Order> orders = List.of(order);

        // when
        when(orderRepository.findAll()).thenReturn(orders);

        // then
        List<Order> result = orderService.getOrders();

        // assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.getFirst().getId());

        verify(orderRepository).findAll();
        verifyNoMoreInteractions(orderRepository);
    }

}
