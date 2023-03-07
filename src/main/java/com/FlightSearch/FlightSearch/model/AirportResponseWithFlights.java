package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.repository.entities.Airport;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AirportResponseWithFlights {
    private int id;
    private String name;
    private String location;
    private String iataCode;
    private String country;
    private Double latitude;
    private Double longitude;
    //private List<Flight> flights;

    public AirportResponseWithFlights(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.location = airport.getLocation();
        this.iataCode = airport.getIataCode();
        this.country = airport.getCountry();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();
        //this.flights =airport.getFlights();

    }
}

