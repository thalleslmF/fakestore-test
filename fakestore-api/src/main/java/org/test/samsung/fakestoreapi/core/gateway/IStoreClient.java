package org.test.samsung.fakestoreapi.core.gateway;

import org.test.samsung.fakestoreapi.core.domain.Order;
import org.test.samsung.fakestoreapi.core.domain.Product;
import org.test.samsung.fakestoreapi.core.domain.User;

import java.util.List;

public interface IStoreClient {

    List<Order> getAllOrders();

    List<User> getAllUsers();


    Order getOrderById(int id);


    Product getProductById(int id);


    User getUserById(int id);

}
