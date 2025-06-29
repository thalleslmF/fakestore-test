package org.samsung.fakestoreapi.infrastructure.client;

import org.samsung.fakestoreapi.core.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productClient", url = "https://fakestoreapi.com")
public interface ProductClient {
    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable("id") int id);
}