package org.test.samsung.fakestoreapi.infrastructure.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.test.samsung.fakestoreapi.core.domain.Order;
import org.test.samsung.fakestoreapi.core.domain.Product;
import org.test.samsung.fakestoreapi.core.domain.User;
import org.test.samsung.fakestoreapi.core.gateway.IStoreClient;
import org.test.samsung.fakestoreapi.infrastructure.client.response.OrderClientResponse;
import org.test.samsung.fakestoreapi.infrastructure.client.response.UserClientResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class FakeStoreClient implements IStoreClient {
    private final FeignFakeStoreClient feignFakeStoreClient;


    @Override
    public List<Order> getAllOrders() {
        log.info("Fetching all orders from FakeStore API");
        var orderResponse = this.feignFakeStoreClient.getAllOrders();

        var orders = orderResponse.stream().map(OrderClientResponse::toOrder).toList();
        log.info("Transformed {} orders to domain objects", orders.size());
        return orders;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users from FakeStore API");
        var userResponse = this.feignFakeStoreClient.getAllUsers();

        var users = userResponse.stream().map(UserClientResponse::toUser).toList();
        log.info("Transformed {} users to domain objects", users.size());
        return users;
    }

    @Override
    public Order getOrderById(int id) {
        log.info("Fetching order with ID={} from FakeStore API", id);
        var orderResponse = this.feignFakeStoreClient.getAOrderById(id);

        var order = orderResponse.toOrder();
        log.info("Transformed order with ID={} to domain object", id);
        log.debug("Order details: {}", order);
        return order;
    }

    @Override
    public Product getProductById(int id) {
        log.info("Fetching product with ID={} from FakeStore API", id);
        var productResponse = this.feignFakeStoreClient.getProductById(id);

        var product = productResponse.toProduct();
        log.info("Transformed product with ID={} to domain object", id);
        log.debug("Product details: {}", product);
        return product;
    }

    @Override
    public User getUserById(int id) {
        log.info("Fetching user with ID={} from FakeStore API", id);
        var userResponse = this.feignFakeStoreClient.getUserById(id);

        var user = userResponse.toUser();
        log.info("Transformed user with ID={} to domain object", id);
        log.debug("User details: {}", user);
        return user;
    }
}