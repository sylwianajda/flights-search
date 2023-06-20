package com.FlightSearch.FlightSearch.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


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
