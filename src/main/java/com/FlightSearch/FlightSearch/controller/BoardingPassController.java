package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.controller.exceptions.IllegalExceptionProcessing;
import com.FlightSearch.FlightSearch.model.BoardingPassBookingRequest;
import com.FlightSearch.FlightSearch.model.BoardingPassResponse;
import com.FlightSearch.FlightSearch.service.BoardingPassService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@IllegalExceptionProcessing
@RequestMapping("/boardingPass")
public class BoardingPassController {
    private final BoardingPassService boardingPassService;

    public BoardingPassController(BoardingPassService boardingPassService) {
        this.boardingPassService = boardingPassService;
    }

    @Transactional
    @PostMapping("/booking/flight/{flightId}")/*/{numberOfPassengers}*/
    ResponseEntity<List<BoardingPassResponse>> postBookingBoardingPass(@PathVariable Long flightId, @RequestBody final BoardingPassBookingRequest boardingPassBookingRequest) {//final List<Passenger> passengers) {
//        if (!flightDataRepository.existsById(flightId)) {
//            return ResponseEntity.notFound().build();
//        }
        List<BoardingPassResponse> boardingPassList = boardingPassService.generateBoardingPassesForAllPassengers(boardingPassBookingRequest, flightId);
        return ResponseEntity.ok(boardingPassList);
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

    @DeleteMapping("/deleteBooking/{boardingPassId}")
    ResponseEntity<String> deleteBoardingPass(@PathVariable Long boardingPassId) {
//        if (!boardingPassRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
        boardingPassService.bookingCancellation(boardingPassId);
        return ResponseEntity.ok("BoardingPass has been deleted");
    }
}
