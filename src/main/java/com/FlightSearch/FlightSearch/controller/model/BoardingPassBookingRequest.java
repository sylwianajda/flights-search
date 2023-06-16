package com.FlightSearch.FlightSearch.controller.model;

import com.FlightSearch.FlightSearch.service.model.Passenger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
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
