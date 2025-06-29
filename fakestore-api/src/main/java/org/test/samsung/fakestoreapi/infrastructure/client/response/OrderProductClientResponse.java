package org.test.samsung.fakestoreapi.infrastructure.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductClientResponse {
    private int productId;
    private int quantity;

}