package com.example.model;


public class User {
    private int id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String phone;
    private Address address;
    private int __v;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Name getName() { return name; }
    public void setName(Name name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public int get__v() { return __v; }
    public void set__v(int __v) { this.__v = __v; }
}

