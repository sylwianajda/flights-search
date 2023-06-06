package com.FlightSearch.FlightSearch.service.model;

import com.FlightSearch.FlightSearch.controller.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.repository.entities.FlightData;
import com.FlightSearch.FlightSearch.service.model.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Flight {
    private long id;

    private String flightNumber;

    private String departureTo;

    private String arrivalTo;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private BigDecimal price;

    private int numberOfSeatsAvailable;

    private Airport airport;


    public Flight() {
    }


    public Flight(FlightData flightData) {
        this.id = flightData.getId();
        this.flightNumber = flightData.getFlightNumber();
        this.departureTo = flightData.getDepartureTo();
        this.arrivalTo = flightData.getArrivalTo();
        this.departureDate = flightData.getDepartureDate();
        this.arrivalDate = flightData.getArrivalDate();
        this.price = flightData.getPrice();
        this.numberOfSeatsAvailable = flightData.getNumberOfSeatsAvailable();
        this.airport = new Airport(flightData.getAirportData());
    }


    public Flight (CreateFlightRequest flightRequest){
        this.id = flightRequest.getId();
        this.flightNumber =flightRequest.getFlightNumber();
        this.departureTo = flightRequest.getDepartureTo();
        this.arrivalTo = flightRequest.getArrivalTo();
        this.departureDate = flightRequest.getDepartureDate();
        this.arrivalDate = flightRequest.getArrivalDate();
        this.price = flightRequest.getPrice();
        this.numberOfSeatsAvailable = flightRequest.getNumberOfSeatsAvailable();
        this.airport = null;
    }

    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departureTo='" + departureTo + '\'' +
                ", arrivalTo='" + arrivalTo + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", price=" + price +
                ", numberOfSeatsAvailable=" + numberOfSeatsAvailable +
                ", airport=" + airport +
                '}';
    }
}
