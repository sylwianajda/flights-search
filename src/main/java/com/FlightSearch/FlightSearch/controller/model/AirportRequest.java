package com.FlightSearch.FlightSearch.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

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
