package com.FlightSearch.FlightSearch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Airport {
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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "airport")
    private List<Flight> flights;

    public Airport(String name, String location, String iataCode, String country, Double latitude, Double longitude) {
        this.name = name;
        this.location = location;
        this.iataCode = iataCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public void updateAirport(Airport source){
        name = source.name;
        location= source.location;
        iataCode= source.iataCode;
        country= source.country;
        latitude= source.latitude;
        longitude= source.longitude;

    }

}
