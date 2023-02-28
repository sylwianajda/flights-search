package com.FlightSearch.FlightSearch.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
@Getter
@Setter
public class Trip {
    @NotBlank(message = "Trip's departure must not be empty")
    private String departureTo;
    @NotBlank
    private String arrivalTo;
    @NotBlank
    private LocalDateTime departureDate;
    @NotBlank
    private boolean returnTrip;
    private LocalDateTime returnDepartureDate;
    @NotBlank
    private int numberOfPassengers;

//    public Trip() {
//    }

    public Trip(String departureTo, String arrivalTo, LocalDateTime departureDate, boolean returnTrip, int numberOfPassengers) {
        this.departureTo = departureTo;
        this.arrivalTo = arrivalTo;
        this.departureDate = departureDate;
        this.returnTrip = returnTrip;
        this.numberOfPassengers = numberOfPassengers;
    }

    public Trip(String departureTo, String arrivalTo, LocalDateTime departureDate, boolean returnTrip, LocalDateTime returnDepartureDate, int numberOfPassengers) {
        this.departureTo = departureTo;
        this.arrivalTo = arrivalTo;
        this.departureDate = departureDate;
        this.returnTrip = returnTrip;
        this.returnDepartureDate = returnDepartureDate;
        this.numberOfPassengers = numberOfPassengers;
    }
}
