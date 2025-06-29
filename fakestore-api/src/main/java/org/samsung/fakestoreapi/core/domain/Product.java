package org.samsung.fakestoreapi.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private int id;
    private int quantity;
    private String title;
    private int price;
    private String description;
    private String category;
    private String image;

    // getters e setters
}