package com.FlightSearch.FlightSearch.data.entities;

import com.FlightSearch.FlightSearch.model.Airport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@AllArgsConstructor
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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "airportData")
    private List<FlightData> flightData;

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
    public static AirportData from(Airport airport) {
        return new AirportData(
                airport.getId(),
                airport.getName(),
                airport.getLocation(),
                airport.getIataCode(),
                airport.getCountry(),
                airport.getLatitude(),
                airport.getLongitude(),
                new ArrayList<FlightData>()
        );
    }

}
