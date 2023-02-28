package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.data.entities.AirportData;
import com.FlightSearch.FlightSearch.data.entities.FlightData;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
public class Flight {

    private String flightNumber;

    private String departureTo;

    private String arrivalTo;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private BigDecimal price;

    private int numberOfSeatsAvailable;

    private Airport airport;

    public static Flight from(FlightRequest flightRequest){
        return new Flight(
                flightRequest.getFlightNumber(),
                flightRequest.getDepartureTo(),
                flightRequest.getArrivalTo(),
                flightRequest.getDepartureDate(),
                flightRequest.getArrivalDate(),
                flightRequest.getPrice(),
                flightRequest.getNumberOfSeatsAvailable(),
                null
        );
    }
    public static Flight from(FlightData flightData){
        return new Flight(
                flightData.getFlightNumber(),
                flightData.getDepartureTo(),
                flightData.getArrivalTo(),
                flightData.getDepartureDate(),
                flightData.getArrivalDate(),
                flightData.getPrice(),
                flightData.getNumberOfSeatsAvailable(),
                null
        );
    }
}
