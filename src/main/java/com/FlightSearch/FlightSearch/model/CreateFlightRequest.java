package com.FlightSearch.FlightSearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
public class CreateFlightRequest {
    @NotBlank
    private long id;
    @NotBlank
    private String flightNumber;
    @NotBlank
    private String departureTo;
    @NotBlank
    private String arrivalTo;
    @NotBlank
    private LocalDateTime departureDate;
    @NotBlank
    private LocalDateTime arrivalDate;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private int numberOfSeatsAvailable;
    @NotBlank
    private int airportId;
}
