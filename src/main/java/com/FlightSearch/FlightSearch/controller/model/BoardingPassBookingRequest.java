package com.FlightSearch.FlightSearch.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BoardingPassBookingRequest {

    private List<Passenger> passengers;

    public BoardingPassBookingRequest() {

    }

}
