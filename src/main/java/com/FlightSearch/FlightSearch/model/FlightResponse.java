package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.repository.entities.Airport;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class FlightResponse {
    private long id;

    private String flightNumber;

    private String departureTo;

    private String arrivalTo;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private BigDecimal price;

    private int numberOfSeatsAvailable;
    public FlightResponse(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.departureTo = flight.getDepartureTo();
        this.arrivalTo = flight.getArrivalTo();
        this.departureDate = flight.getDepartureDate();
        this.arrivalDate = flight.getArrivalDate();
        this.price = flight.getPrice();
        this.numberOfSeatsAvailable = flight.getNumberOfSeatsAvailable();

    }
}
