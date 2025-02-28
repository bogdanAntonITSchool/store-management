package ro.itschool.store_management.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ro.itschool.store_management.persistence.entity.Address;
import ro.itschool.store_management.persistence.entity.Client;
import ro.itschool.store_management.persistence.entity.Order;
import ro.itschool.store_management.persistence.entity.Product;
import ro.itschool.store_management.persistence.repository.ClientRepository;
import ro.itschool.store_management.persistence.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @ExtendWith annotation is used to tell JUnit to use the SpringExtension class to run the tests.
// SpringExtension class is a class provided by the Spring Framework that integrates the Spring TestContext Framework with JUnit 5.
@ExtendWith(SpringExtension.class)
// @SpringBootTest annotation is used to tell JUnit to bootstrap the Spring application context before running the tests.
@SpringBootTest
// @AutoConfigureMockMvc annotation is used to tell Spring to automatically configure the MockMvc instance.
// MockMvc is a class provided by Spring that is used to test Spring MVC controllers.
@AutoConfigureMockMvc
class OrderControllerIT {

    // @Autowired annotation is used to inject the MockMvc instance into the test class.
    @Autowired
    private MockMvc mockMvc;

    // @MockitoBean annotation is used to tell Spring to create a mock object of the specified type.
    // In this case, we are creating mock objects of the ClientRepository and OrderRepository classes.
    // We don't want to use the real implementations of these classes because we want to control their behavior in the tests.
    @MockitoBean
    private ClientRepository clientRepository;

    @MockitoBean
    private OrderRepository orderRepository;

    @Test
    void testGetOrders() throws Exception {

        Order order = new Order();
        Client client = new Client();
        Address address = new Address();
        Product product = new Product();

        product.setId(1L);

        order.setId(100L);
        order.setClient(client);
        order.setProducts(Set.of(product));

        client.setName("John Doe");
        client.setAddress(address);

        address.setCity("New York");

        when(orderRepository.findAll())
                .thenReturn(List.of(order));

        // We are testing the GET /api/orders endpoint.
        // We expect the response to have a status code of 200 (OK).
        // We expect the response to be a JSON array.
        // We expect the first element in the array to have an id of 100. etc...
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(100))
                .andExpect(jsonPath("$[0].client.name").value("John Doe"))
                .andExpect(jsonPath("$[0].client.address.city").value("New York"))
                .andExpect(jsonPath("$[0].products").isArray())
                .andExpect(jsonPath("$[0].products[0].id").value(1));

    }

    @Test
    void testCreateOrder() throws Exception {

        Client client = new Client();

        client.setId(1L);
        client.setName("John Doe");

        Order order = new Order();

        order.setId(1L);
        order.setTotalPrice(100.0);
        order.setStatus("NEW");
        order.setClient(client);

        when(clientRepository.findById(any()))
                .thenReturn(Optional.of(client));

        when(orderRepository.save(any(Order.class)))
                .thenReturn(order);

        mockMvc.perform(
                        post("/api/orders")
                                // We are sending a JSON object as the request body.
                                // We use text blocks to define the JSON object.
                                .content("""
                                        {
                                            "totalPrice": 100.0,
                                            "status": "NEW",
                                            "client": 1,
                                            "products": [1, 2, 3]
                                        }
                                        """)
                                .contentType("application/json")
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.totalPrice").value(100.0))
                .andExpect(jsonPath("$.status").value("NEW"))
                .andExpect(jsonPath("$.clientId").value(1))
                .andExpect(jsonPath("$.products").isArray());

    }

}
