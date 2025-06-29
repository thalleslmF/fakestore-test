package org.samsung.fakestoreapi.infrastructure.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.samsung.fakestoreapi.core.domain.Product;
import org.samsung.fakestoreapi.core.domain.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductClientResponse {
    private int productId;
    private int quantity;

}