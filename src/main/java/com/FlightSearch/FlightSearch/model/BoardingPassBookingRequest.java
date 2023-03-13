package com.FlightSearch.FlightSearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BoardingPassBookingRequest {

    public BoardingPassBookingRequest() {

    }
    private long boardingPassId;

    private List<Passenger> passengers;

}
