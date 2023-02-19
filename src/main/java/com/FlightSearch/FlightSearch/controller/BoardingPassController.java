package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.model.Flight;
import com.FlightSearch.FlightSearch.model.Passenger;
import com.FlightSearch.FlightSearch.repository.BoardingPassRepository;
import com.FlightSearch.FlightSearch.repository.FlightRepository;
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
    private final FlightRepository flightRepository;



    public BoardingPassController(BoardingPassServices boardingPassServices, FlightRepository flightRepository) {
        this.boardingPassServices = boardingPassServices;
        this.flightRepository = flightRepository;
    }

    @Transactional
    @PostMapping("/booking/flight/{id}")/*/{numberOfPassengers}*/
    ResponseEntity<?> postBookingBoardingPass(@PathVariable Long id, @RequestBody List<Passenger> passengers) {
        if (!flightRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Flight flight = flightRepository.findById(id).get();
        if ((boardingPassServices.generateBoardingPassesForAllPassengers(passengers, flight)) != null) {
            return ResponseEntity.ok(boardingPassServices.generateBoardingPassesForAllPassengers(passengers, flight));
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


    @PostMapping("/deleteBooking/{id}")
    String deleteBoardingPass(@PathVariable Long id) {
//        boardingPassRepository.findById(id).ifPresent(boardingPass ->
//                boardingPassRepository.deleteById(id));
//        Long flightId = boardingPassRepository.findById(id).ifPresent(boardingPass ->
//                boardingPass.getFlight().getId());
  //      Flight flight= flightRepository.findById(flight).get();
   //     FlightState flightState = new FlightState(flightRepository, flight);

        return null;
    }
}
