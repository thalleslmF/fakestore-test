package org.fakestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geolocation {
    private String lat;
    private String longitude;

    // Jackson vai precisar de mapeamento porque no JSON vem como "long"
    public String getLat() { return lat; }
    public void setLat(String lat) { this.lat = lat; }

    public String getLongitude() { return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }
}
