package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.repository.entities.BoardingPassData;
import com.FlightSearch.FlightSearch.model.Passenger;
import com.FlightSearch.FlightSearch.repository.sqlRepository.BoardingPassDataRepository;
import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightDataRepository;
import com.FlightSearch.FlightSearch.service.BoardingPassServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boardingPass")
public class BoardingPassController {
    private final BoardingPassServices boardingPassServices;
    private final FlightDataRepository flightDataRepository;
    private final BoardingPassDataRepository boardingPassDataRepository;


    public BoardingPassController(BoardingPassServices boardingPassServices, FlightDataRepository flightDataRepository,
                                  BoardingPassDataRepository boardingPassDataRepository) {
        this.boardingPassServices = boardingPassServices;
        this.flightDataRepository = flightDataRepository;
        this.boardingPassDataRepository = boardingPassDataRepository;
    }

    @Transactional
    @PostMapping("/booking/flight/{flightId}")/*/{numberOfPassengers}*/
    ResponseEntity<?> postBookingBoardingPass(@PathVariable Long flightId, @RequestBody List<Passenger> passengers) {
        if (!flightDataRepository.existsById(flightId)) {
            return ResponseEntity.notFound().build();
        }
        List<BoardingPassData> boardingPassDataList = boardingPassServices.generateBoardingPassesForAllPassengers(passengers, flightId);
        if ((boardingPassDataList) != null) {
            return ResponseEntity.ok(boardingPassDataList);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No seats available. Please try booking a different flight");

        }
    }
//        Flight flight= flightRepository.findById(id).get();
//        FlightState flightState = new FlightState(flightRepository, flight);
//        BoardingPass result = new BoardingPass(firstName, lastName, flight);
//        if (/*!flightState.availableSeatsIsFinished()*/flightState.getCurrentNumberOfSeatsAvailable() >= numberOfPassengers){
//                boardingPassRepository.save(result);
//                for( int x = 0; x <= numberOfPassengers; x++){
//                flightState.nextOccupiedSeat();}
//            return ResponseEntity.ok(result);
//        } else {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("No seats available. Please try booking a different flight");
//        }

    @Transactional
    @DeleteMapping("/deleteBooking/{id}")
    ResponseEntity<?> deleteBoardingPass(@PathVariable Long id) {
//        if (!boardingPassRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        boardingPassServices.deleteBoardingPass(id);
//        return ResponseEntity.ok("BoardingPass has been deleted");
        return null;
    }
}
