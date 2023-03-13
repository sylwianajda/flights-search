package com.FlightSearch.FlightSearch.repository.entities;

import com.FlightSearch.FlightSearch.model.BoardingPassBookingRequest;
import com.FlightSearch.FlightSearch.model.Passenger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

//    public List<BoardingPass> getListOfBoardingPasses(BoardingPassBookingRequest boardingPassBookingRequest, ) {
//        List<BoardingPass> boardingPasses = new ArrayList<>();
//        List<Passenger> passengers = boardingPassBookingRequest.getPassengers();
//
//        for (Passenger passenger : passengers) {
//            BoardingPass boardingPass = new BoardingPass(passenger);
//            boardingPasses.add(boardingPass);
//        }
//        return boardingPasses;
//    }
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
