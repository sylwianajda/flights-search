package com.FlightSearch.FlightSearch.service.model;

import com.FlightSearch.FlightSearch.controller.model.Passenger;
import com.FlightSearch.FlightSearch.repository.entities.BoardingPassData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoardingPass {
    private long boardingPassId;
    private String firstName;
    private String lastName;
    private Flight flight;



    public BoardingPass(BoardingPassData boardingPassData) {
            this.boardingPassId = boardingPassData.getBoardingPassId();
            this.firstName = boardingPassData.getFirstName();
            this.lastName = boardingPassData.getLastName();
            this.flight = new Flight(boardingPassData.getFlightData());

    }

    public BoardingPass(String firstName, String lastName, Flight flight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.flight = flight;
    }

    public BoardingPass(Passenger passenger) {
        this.boardingPassId = 0;
        this.firstName = passenger.getFirstName();
        this.lastName = passenger.getLastName();
        this.flight = null;
    }

}
