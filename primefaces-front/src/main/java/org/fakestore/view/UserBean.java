package org.fakestore.view;


import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import org.fakestore.model.Order;
import org.fakestore.model.User;
import org.fakestore.service.OrderService;
import org.fakestore.service.UserService;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("userBean")
@ViewScoped
public class UserBean implements Serializable {
    @Inject
    UserService userService;



    @Inject
    OrderService orderService;
    private String selectedUser;
    private Date startDate;
    private Date endDate;
    private String orderNumber;

    private List<User> users = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    private List<Order> orders = new ArrayList<>();

    // Getters e Setters

    public String getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(String selectedUser) {
        this.selectedUser = selectedUser;
    }


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() throws IOException {
        return userService.fetchUsers();
    }

    public void find() throws IOException {
        Instant instantStart = Instant.ofEpochMilli(startDate.getTime() - ZoneOffset.ofHours(+3).getTotalSeconds() * 1000L);
        Instant instantEnd = Instant.ofEpochMilli(endDate.getTime() - ZoneOffset.ofHours(+3).getTotalSeconds() * 1000L);
        System.out.println("Order: "+ this.orderNumber);
        System.out.println("User: "+ this.selectedUser);
        System.out.println("Start date: "+ instantStart);
        System.out.println("End date: "+ instantEnd);
        this.orders = this.orderService.fetchOrders(orderNumber, instantStart, instantEnd, selectedUser);
    }

    public void clean() {
        System.out.println("limpando...");
        selectedUser = null;
        startDate = null;
        endDate = null;
        orderNumber = null;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

}
