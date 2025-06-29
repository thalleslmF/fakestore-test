package com.example.service;

import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.faces.bean.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name= "userService")
@ApplicationScoped
public class UserService implements Serializable {

    public List<User> fetchUsers() throws IOException {

        URL url = new URL("http://localhost:8081/api/v1/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");


        try (InputStream is = conn.getInputStream()) {
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            var mapper = new ObjectMapper();
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        } catch (Exception e) {
            System.out.printf("Error while getting users: %s", e.getMessage());
            return null;
        }
    }
}
