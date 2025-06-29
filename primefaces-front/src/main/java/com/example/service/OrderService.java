package com.example.service;

import com.example.model.Order;
import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

@ManagedBean(name= "orderService")
@ApplicationScoped
public class OrderService implements Serializable {

    public List<Order> fetchOrders(String userId, Instant startDate, Instant endDate, String orderId) throws IOException {

        var url = new URL(String.format(
                "http://localhost:8081/api/v1/orders?userId=%s&orderStartDate=%s&orderEndDate=%s&orderId=%s",
                userId, startDate, endDate, orderId
        ));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");


        try (InputStream is = conn.getInputStream()) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            var mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            System.out.println("JSON Response: " + json);
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Order.class));
        } catch (Exception e) {
            System.out.printf("Error while getting orders: %s", e.getMessage());
            return null;
        }
    }
}
