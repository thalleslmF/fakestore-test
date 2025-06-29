package org.samsung.fakestoreapi.infrastructure.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.samsung.fakestoreapi.core.domain.Order;
import org.samsung.fakestoreapi.core.domain.Product;
import org.samsung.fakestoreapi.core.domain.User;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderClientResponse {
    private int id;
    private int userId;
    private Instant date;
    private List<OrderProductClientResponse> products;

    public Order toOrder() {
        return Order.builder()
                .id(this.id)
                .date(this.date)
                .user(User.builder()
                        .id(this.userId)
                        .build())
                .products(this.products.stream()
                        .map(product -> Product.builder()
                                .id(product.getProductId())
                                .quantity(product.getQuantity())
                                .build())
                        .toList())
                .build();
    }
}