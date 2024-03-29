package com.FlightSearch.FlightSearch.service.model;

import com.FlightSearch.FlightSearch.controller.model.AirportRequest;
import com.FlightSearch.FlightSearch.repository.entities.AirportData;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

    public Airport(String name, String location, String iataCode, String country, Double latitude, Double longitude) {
        this.name = name;
        this.location = location;
        this.iataCode = iataCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
//    private List<Flight> flights;


    public Airport(AirportData airportData) {
        this.id = airportData.getId();
        this.name = airportData.getName();
        this.location = airportData.getLocation();
        this.iataCode = airportData.getIataCode();
        this.country = airportData.getCountry();
        this.latitude = airportData.getLatitude();
        this.longitude = airportData.getLongitude();
//        this.flights = airportData.getFlightsData().stream()
//                .map(Flight::new)
//                .collect(Collectors.toList());
    }
    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                //", flights=" + flights.toString() +
                "}";}

    public Airport(AirportRequest airportRequest) {
        this.id = airportRequest.getId();
        this.name = airportRequest.getName();
        this.location = airportRequest.getLocation();
        this.iataCode = airportRequest.getIataCode();
        this.country = airportRequest.getCountry();
        this.latitude = airportRequest.getLatitude();
        this.longitude = airportRequest.getLongitude();

    }
    public void updateAirport(Airport preparedAirportToUpdate) {
                name = preparedAirportToUpdate.name;
                location = preparedAirportToUpdate.location;
                iataCode = preparedAirportToUpdate.iataCode;
                country = preparedAirportToUpdate.country;
                latitude = preparedAirportToUpdate.latitude;
                longitude =preparedAirportToUpdate.longitude;

    }



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

}
