package org.fakestore.config;

public class ConfigLoader {

    public String getUrlOrders() {
        if (System.getenv("HOST_ORDERS") != null) {
            return System.getenv("HOST_ORDERS");
        } else {
            return "http://localhost:8081"
        }

    }

 }

