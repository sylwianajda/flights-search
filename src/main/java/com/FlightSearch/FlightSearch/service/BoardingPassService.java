package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.controller.exceptions.NonAvailableSeatsException;
import com.FlightSearch.FlightSearch.controller.model.BoardingPassBookingRequest;
import com.FlightSearch.FlightSearch.controller.model.BoardingPassResponse;
import com.FlightSearch.FlightSearch.service.model.Passenger;
import com.FlightSearch.FlightSearch.service.model.BoardingPass;
import com.FlightSearch.FlightSearch.service.model.Flight;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardingPassService {
    private SqlRepository sqlRepository;

    public BoardingPassService(SqlRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
    }
    @Transactional
    public List<BoardingPassResponse> generateBoardingPassesForAllPassengers(BoardingPassBookingRequest boardingPassBookingRequest, Long flightId) throws NonAvailableSeatsException {
        Flight flight = sqlRepository.findFlightById(flightId).get();
        List<BoardingPass> listOfBoardingPassesFromRequestForAllPassengers = getListOfBoardingPassesFromBoardingPassBookingRequest(boardingPassBookingRequest, flight);
        List<BoardingPassResponse> boardingPassesResponse = new ArrayList<>();
        Integer numberOfSeatsAvailable = sqlRepository.getCurrentNumberOfSeatsAvailable(flightId);

        if (numberOfSeatsAvailable >= listOfBoardingPassesFromRequestForAllPassengers.size()) {
            listOfBoardingPassesFromRequestForAllPassengers.forEach(boardingPass -> {
                Long savedBoardingPassDataId = sqlRepository.saveBoardingPass(boardingPass).getBoardingPassId();
                 sqlRepository.decreaseSeatsAvailable(flightId);
                BoardingPassResponse boardingPassResponse = makeBoardingPassResponseFromBoardingPass(savedBoardingPassDataId);
                boardingPassesResponse.add(boardingPassResponse);
            });
            return boardingPassesResponse;
        } else
            throw new NonAvailableSeatsException();
    }
    public BoardingPassResponse makeBoardingPassResponseFromBoardingPass(Long boardingPassId){
        return new BoardingPassResponse(sqlRepository.findBoardingPassById(boardingPassId).get());
    }

    public List<BoardingPass> getListOfBoardingPassesFromBoardingPassBookingRequest(BoardingPassBookingRequest boardingPassBookingRequest, Flight flight) {
        List<BoardingPass> boardingPasses = new ArrayList<>();
        List<Passenger> passengers = boardingPassBookingRequest.getPassengers();
        passengers.stream().forEach(passenger -> {
            BoardingPass boardingPass = new BoardingPass(passenger);
            boardingPass.setFlight(flight);
            boardingPasses.add(boardingPass);
        }
        );

        return boardingPasses;
    }

    @Transactional
    public void bookingCancellation(Long boardingPassId) {
        BoardingPass boardingPass = sqlRepository.findBoardingPassById(boardingPassId).get();
        Long flightId = boardingPass.getFlight().getId();
        sqlRepository.deleteBoardingPassById(boardingPassId);
        sqlRepository.increaseSeatsAvailable(flightId);
    }



}
