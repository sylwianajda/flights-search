package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.BoardingPass;
import com.FlightSearch.FlightSearch.model.Flight;
import com.FlightSearch.FlightSearch.model.FlightState;
import com.FlightSearch.FlightSearch.model.Passenger;
import com.FlightSearch.FlightSearch.repository.BoardingPassRepository;
import com.FlightSearch.FlightSearch.repository.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardingPassServices {
    private final BoardingPassRepository boardingPassRepository;
    private final FlightRepository flightRepository;

    public BoardingPassServices(BoardingPassRepository boardingPassRepository, FlightRepository flightRepository) {
        this.boardingPassRepository = boardingPassRepository;
        this.flightRepository = flightRepository;
    }

    public List<BoardingPass> generateBoardingPassesForAllPassengers(List<Passenger> passengers, Flight flight) {
        FlightState flightState = new FlightState(flightRepository, flight);
        List<BoardingPass> boardingPasses = new ArrayList<>();
        if (/*!flightState.availableSeatsIsFinished()*/flightState.getCurrentNumberOfSeatsAvailable() >= passengers.size()) {
            for (Passenger passenger : passengers) {
                BoardingPass result = new BoardingPass(passenger.getFirstName(), passenger.getLastName(), flight);
                boardingPassRepository.save(result);
                flightState.nextOccupiedSeat();
                boardingPasses.add(result);
            }
//               for(int x = 0; x <= passengers.size(); x++){
//               flightState.nextOccupiedSeat();}
            return boardingPasses; //ResponseEntity.ok(result);
        } else {
            return null; //ResponseEntity.status(HttpStatus.CONFLICT).body("No seats available. Please try booking a different flight");
        }
    }

}
