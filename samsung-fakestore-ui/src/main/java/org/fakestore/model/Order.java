package org.fakestore.model;



import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {

    private int id;
    private User user;
    private Instant date;

    private Double totalValue;
    private List<Product> products;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ZonedDateTime zonedDateTime = this.date.atZone(ZoneId.of("America/Sao_Paulo"));

        return formatter.format(zonedDateTime);
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public void calculateTotalValue() {
        this.totalValue = this.products.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }
}

