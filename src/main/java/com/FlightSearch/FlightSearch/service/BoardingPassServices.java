package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.data.entities.BoardingPassData;
import com.FlightSearch.FlightSearch.data.entities.FlightData;
import com.FlightSearch.FlightSearch.model.FlightState;
import com.FlightSearch.FlightSearch.model.Passenger;
import com.FlightSearch.FlightSearch.data.repository.sqlRepository.BoardingPassRepository;
import com.FlightSearch.FlightSearch.data.repository.sqlRepository.FlightRepository;
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

    public List<BoardingPassData> generateBoardingPassesForAllPassengers(List<Passenger> passengers, Long id) {
        FlightData flightData = flightRepository.findById(id).get();
        FlightState flightState = new FlightState(flightRepository, flightData);
        List<BoardingPassData> boardingPassData = new ArrayList<>();

        if (/*!flightState.availableSeatsIsFinished()*/flightState.getCurrentNumberOfSeatsAvailable() >= passengers.size()) {
            passengers.forEach(passenger -> {
                BoardingPassData result = new BoardingPassData(passenger.getFirstName(), passenger.getLastName(), flightData);
                boardingPassRepository.save(result);
                flightState.nextOccupiedSeat();
                boardingPassData.add(result);
            });
//               for(int x = 0; x <= passengers.size(); x++){
//               flightState.nextOccupiedSeat();}
            return boardingPassData; //ResponseEntity.ok(result);
        } else {
            return null; //ResponseEntity.status(HttpStatus.CONFLICT).body("No seats available. Please try booking a different flight");
        }
    }
    public void deleteBoardingPass(Long id) {
        Long flightId = boardingPassRepository.findById(id).get().getFlightData().getId();
        boardingPassRepository.findById(id).ifPresent(boardingPass ->
                boardingPassRepository.deleteById(id));
//        Long flightId = boardingPassRepository.findById(id).ifPresent(boardingPass ->
//                boardingPass.get().getFlight().getId());
            FlightData flightData = flightRepository.findById(flightId).get();
            FlightState flightState = new FlightState(flightRepository, flightData);
            flightState.nextAvailableSeat();
    }

}
