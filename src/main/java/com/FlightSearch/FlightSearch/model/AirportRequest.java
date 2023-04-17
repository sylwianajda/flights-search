package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.repository.entities.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AirportRequest {
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String location;
    @NotBlank
    private String iataCode;
    @NotBlank
    private String country;
    @NotBlank
    private Double latitude;
    @NotBlank
    private Double longitude;


}
