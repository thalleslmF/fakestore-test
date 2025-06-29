package com.example.view;

import com.example.model.Product;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
    }

    public List<Product> getProdutos() {
        return products;
    }
}
