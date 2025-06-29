package com.example.view;

import com.example.model.Order;
import com.example.model.User;
import com.example.service.OrderService;
import com.example.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {
    @ManagedProperty("#{userService}")
    UserService userService;



    @ManagedProperty("#{orderService}")
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
