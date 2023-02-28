package com.FlightSearch.FlightSearch.model;

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

    public static AirportResponse from(Airport airport) {
        return new AirportResponse(
                airport.getId(),
                airport.getName(),
                airport.getLocation(),
                airport.getIataCode(),
                airport.getCountry(),
                airport.getLatitude(),
                airport.getLatitude()
        );
    }
}
