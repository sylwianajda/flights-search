package com.FlightSearch.FlightSearch.controller.model;

import com.FlightSearch.FlightSearch.service.model.Passenger;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BoardingPassBookingRequest {
    @NotEmpty
    private List<Passenger> passengers;

    public BoardingPassBookingRequest() {

    }

}
