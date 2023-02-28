package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.data.entities.AirportData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Airport {
    private int id;
    private String name;
    private String location;
    private String iataCode;
    private String country;
    private Double latitude;
    private Double longitude;



    public static Airport from(AirportRequest airportRequest){
        return new Airport(
                airportRequest.getId(),
                airportRequest.getName(),
                airportRequest.getLocation(),
                airportRequest.getIataCode(),
                airportRequest.getCountry(),
                airportRequest.getLatitude(),
                airportRequest.getLongitude()
        );
    }
    public static Airport from(AirportData airportData){
        return new Airport(
                airportData.getId(),
                airportData.getName(),
                airportData.getLocation(),
                airportData.getIataCode(),
                airportData.getCountry(),
                airportData.getLatitude(),
                airportData.getLongitude()
        );
    }
}
