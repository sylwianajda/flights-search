package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.repository.entities.BoardingPass;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
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

    public BoardingPassResponse() {
    }
    private Flight flight;

    public BoardingPassResponse(BoardingPass boardingPass) {
        this.boardingPassId = boardingPass.getBoardingPassId();
        this.firstName = boardingPass.getFirstName();
        this.lastName = boardingPass.getLastName();
        this.flight = boardingPass.getFlight();

    }
}
