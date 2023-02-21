package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.BoardingPass;
import com.FlightSearch.FlightSearch.model.Flight;
import com.FlightSearch.FlightSearch.model.FlightState;
import com.FlightSearch.FlightSearch.model.Passenger;
import com.FlightSearch.FlightSearch.repository.sqlRepository.BoardingPassRepository;
import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightRepository;
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

    public List<BoardingPass> generateBoardingPassesForAllPassengers(List<Passenger> passengers, Long id) {
        Flight flight = flightRepository.findById(id).get();
        FlightState flightState = new FlightState(flightRepository, flight);
        List<BoardingPass> boardingPasses = new ArrayList<>();

        if (/*!flightState.availableSeatsIsFinished()*/flightState.getCurrentNumberOfSeatsAvailable() >= passengers.size()) {
            passengers.forEach(passenger -> {
                BoardingPass result = new BoardingPass(passenger.getFirstName(), passenger.getLastName(), flight);
                boardingPassRepository.save(result);
                flightState.nextOccupiedSeat();
                boardingPasses.add(result);
            });
//               for(int x = 0; x <= passengers.size(); x++){
//               flightState.nextOccupiedSeat();}
            return boardingPasses; //ResponseEntity.ok(result);
        } else {
            return null; //ResponseEntity.status(HttpStatus.CONFLICT).body("No seats available. Please try booking a different flight");
        }
    }
    public void deleteBoardingPass(Long id) {
        Long flightId = boardingPassRepository.findById(id).get().getFlight().getId();
        boardingPassRepository.findById(id).ifPresent(boardingPass ->
                boardingPassRepository.deleteById(id));
//        Long flightId = boardingPassRepository.findById(id).ifPresent(boardingPass ->
//                boardingPass.get().getFlight().getId());
            Flight flight= flightRepository.findById(flightId).get();
            FlightState flightState = new FlightState(flightRepository, flight);
            flightState.nextAvailableSeat();
    }

}
