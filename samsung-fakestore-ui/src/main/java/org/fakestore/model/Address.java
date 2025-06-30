package org.fakestore.model;

public class Address {
    private Geolocation geolocation;
    private String city;
    private String street;
    private int number;
    private String zipcode;

    // Getters e Setters
    public Geolocation getGeolocation() { return geolocation; }
    public void setGeolocation(Geolocation geolocation) { this.geolocation = geolocation; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }
}
