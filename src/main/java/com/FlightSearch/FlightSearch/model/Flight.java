package com.FlightSearch.FlightSearch.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Flight {

    private long id;

    private String flightNumber;

    private String departureTo;

    private String arrivalTo;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private BigDecimal price;

    private int numberOfSeatsAvailable;
}
