package com.FlightSearch.FlightSearch.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "flights")
public class FlightData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "airport_id")
    private AirportData airportData;


    public FlightData(long id, String flightNumber, String departureTo, String arrivalTo,
                      LocalDateTime departureDate, LocalDateTime arrivalDate, BigDecimal price,
                      int numberOfSeatsAvailable, AirportData airportData) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureTo = departureTo;
        this.arrivalTo = arrivalTo;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
        this.numberOfSeatsAvailable = numberOfSeatsAvailable;
        this.airportData = airportData;
    }

    public FlightData(Flight flight) {
        this.id = flight.getId();
        this.flightNumber = flight.getFlightNumber();
        this.departureTo = flight.getDepartureTo();
        this.arrivalTo = flight.getArrivalTo();
        this.departureDate = flight.getDepartureDate();
        this.arrivalDate = flight.getArrivalDate();
        this.price = flight.getPrice();
        this.numberOfSeatsAvailable = flight.getNumberOfSeatsAvailable();
        this.airportData = new AirportData(flight.getAirport());

    }

    public long getId() {

        return id;
    }


}
