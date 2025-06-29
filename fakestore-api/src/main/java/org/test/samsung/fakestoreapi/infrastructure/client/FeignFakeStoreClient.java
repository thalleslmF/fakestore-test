package org.test.samsung.fakestoreapi.infrastructure.client;

import org.test.samsung.fakestoreapi.infrastructure.client.response.OrderClientResponse;
import org.test.samsung.fakestoreapi.infrastructure.client.response.ProductClientResponse;
import org.test.samsung.fakestoreapi.infrastructure.client.response.UserClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cartClient", url = "${fakestore.api.url}")
public interface FeignFakeStoreClient {
    @GetMapping("/carts")
    List<OrderClientResponse> getAllOrders();

    @GetMapping("/carts/{id}")
    OrderClientResponse getAOrderById(@PathVariable("id") int id);

    @GetMapping("/products/{id}")
    ProductClientResponse getProductById(@PathVariable("id") int id);

    @GetMapping("/users/{id}")
    UserClientResponse getUserById(@PathVariable("id") int id);

    @GetMapping("/users")
    List<UserClientResponse> getAllUsers();
}