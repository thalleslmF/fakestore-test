package org.test.samsung.fakestoreapi.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private int id;
    private User user;
    private Instant date;
    private List<Product> products;

}