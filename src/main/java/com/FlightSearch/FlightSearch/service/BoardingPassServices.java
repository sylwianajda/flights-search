package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.repository.entities.BoardingPassData;
import com.FlightSearch.FlightSearch.repository.entities.FlightData;
import com.FlightSearch.FlightSearch.model.FlightState;
import com.FlightSearch.FlightSearch.model.Passenger;
import com.FlightSearch.FlightSearch.repository.sqlRepository.BoardingPassDataRepository;
import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightDataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardingPassServices {
    private final BoardingPassDataRepository boardingPassDataRepository;
    private final FlightDataRepository flightDataRepository;

    public BoardingPassServices(BoardingPassDataRepository boardingPassDataRepository, FlightDataRepository flightDataRepository) {
        this.boardingPassDataRepository = boardingPassDataRepository;
        this.flightDataRepository = flightDataRepository;
    }

    public List<BoardingPassData> generateBoardingPassesForAllPassengers(List<Passenger> passengers, Long id) {
        FlightData flightData = flightDataRepository.findById(id).get();
        FlightState flightState = new FlightState(flightDataRepository, flightData);
        List<BoardingPassData> boardingPassData = new ArrayList<>();

        if (/*!flightState.availableSeatsIsFinished()*/flightState.getCurrentNumberOfSeatsAvailable() >= passengers.size()) {
            passengers.forEach(passenger -> {
                BoardingPassData result = new BoardingPassData(passenger.getFirstName(), passenger.getLastName(), flightData);
                boardingPassDataRepository.save(result);
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
        Long flightId = boardingPassDataRepository.findById(id).get().getFlightData().getId();
        boardingPassDataRepository.findById(id).ifPresent(boardingPass ->
                boardingPassDataRepository.deleteById(id));
//        Long flightId = boardingPassRepository.findById(id).ifPresent(boardingPass ->
//                boardingPass.get().getFlight().getId());
            FlightData flightData = flightDataRepository.findById(flightId).get();
            FlightState flightState = new FlightState(flightDataRepository, flightData);
            flightState.nextAvailableSeat();
    }

}
