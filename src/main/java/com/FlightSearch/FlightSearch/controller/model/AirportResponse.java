package com.FlightSearch.FlightSearch.controller.model;

import com.FlightSearch.FlightSearch.service.model.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AirportResponse {
    private int id;
    private String name;
    private String location;
    private String iataCode;
    private String country;
    private Double latitude;
    private Double longitude;


    public AirportResponse(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.location = airport.getLocation();
        this.iataCode = airport.getIataCode();
        this.country = airport.getCountry();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();

    }

}
