package org.fakestore.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.fakestore.config.ConfigLoader;
import org.fakestore.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

@Named("orderService")
@ApplicationScoped
public class OrderService implements Serializable {

    private String getApiURL() {
        return new ConfigLoader().get("store.api.host");
    }

    public List<Order> fetchOrders(String userId, Instant startDate, Instant endDate, String orderId) throws IOException {
        var baseUrl = getApiURL();
        var url = new URL(String.format(
                baseUrl + "/api/v1/orders?userId=%s&orderStartDate=%s&orderEndDate=%s&orderId=%s",
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
            System.out.printf("Error while getting orders: %s%n", e.getMessage());
            return null;
        }
    }
}
