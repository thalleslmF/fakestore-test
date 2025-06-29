package org.samsung.fakestoreapi.infrastructure.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.samsung.fakestoreapi.core.domain.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductClientResponse {
    private int id;
    private int quantity;
    private String title;
    private int price;
    private String description;
    private String category;
    private String image;

    public Product toProduct() {
        return Product.builder()
                .quantity(this.quantity)
                .id(this.id)
                .title(this.title)
                .price(this.price)
                .description(this.description)
                .category(this.category)
                .image(this.image)
                .build();
    }
}