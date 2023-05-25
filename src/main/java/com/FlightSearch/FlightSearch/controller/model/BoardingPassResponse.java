package com.FlightSearch.FlightSearch.controller.model;

import com.FlightSearch.FlightSearch.service.model.BoardingPass;
import com.FlightSearch.FlightSearch.service.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoardingPassResponse {
    private long boardingPassId;
    private String firstName;
    private String lastName;
    private Flight flight;
    public BoardingPassResponse() {
    }

    public BoardingPassResponse(BoardingPass boardingPass) {
        this.boardingPassId = boardingPass.getBoardingPassId();
        this.firstName = boardingPass.getFirstName();
        this.lastName = boardingPass.getLastName();
        this.flight = boardingPass.getFlight();

    }
}
