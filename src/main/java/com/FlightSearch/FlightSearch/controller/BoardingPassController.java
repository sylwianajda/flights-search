package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.controller.exceptions.IllegalExceptionProcessing;
import com.FlightSearch.FlightSearch.controller.exceptions.NonAvailableSeatsException;
import com.FlightSearch.FlightSearch.controller.model.BoardingPassBookingRequest;
import com.FlightSearch.FlightSearch.controller.model.BoardingPassResponse;
import com.FlightSearch.FlightSearch.service.BoardingPassService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/booking/flight/{flightId}")
    ResponseEntity<List<BoardingPassResponse>> postBookingBoardingPass(@PathVariable Long flightId, @RequestBody final BoardingPassBookingRequest boardingPassBookingRequest) throws NonAvailableSeatsException {
        List<BoardingPassResponse> boardingPassList = boardingPassService.generateBoardingPassesForAllPassengers(boardingPassBookingRequest, flightId);
        return ResponseEntity.ok(boardingPassList);
    }

    @DeleteMapping("/deleteBooking/{boardingPassId}")
    ResponseEntity<String> deleteBoardingPass(@PathVariable Long boardingPassId) {
        boardingPassService.bookingCancellation(boardingPassId);
        return ResponseEntity.ok("BoardingPass has been deleted");
    }
}
