package com.FlightSearch.FlightSearch.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "airport")
public class AirportData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String name;
    private String location;
    private String iataCode;
    private String country;
    private Double latitude;
    private Double longitude;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "airportData")
    private List<FlightData> flightsData;

    public AirportData(int id, String name, String location, String iataCode, String country, Double latitude, Double longitude, List<FlightData> flightsData) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.iataCode = iataCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.flightsData = flightsData;
    }

    public AirportData(String name, String location, String iataCode, String country, Double latitude, Double longitude) {
        this.name = name;
        this.location = location;
        this.iataCode = iataCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public void updateAirport(AirportData source){
        name = source.name;
        location= source.location;
        iataCode= source.iataCode;
        country= source.country;
        latitude= source.latitude;
        longitude= source.longitude;

    }
    public AirportData(Airport airport) {
        this.id = airport.getId();
        this.name = airport.getName();
        this.location = airport.getLocation();
        this.iataCode = airport.getIataCode();
        this.country = airport.getCountry();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();
        this.flightsData = new ArrayList<>();
    }

}
