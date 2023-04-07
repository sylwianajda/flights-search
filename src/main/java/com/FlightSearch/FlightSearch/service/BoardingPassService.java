package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.*;
import com.FlightSearch.FlightSearch.repository.entities.BoardingPass;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
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
    public List<BoardingPassResponse> generateBoardingPassesForAllPassengers(BoardingPassBookingRequest boardingPassBookingRequest, Long flightId) {
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
        } else {
            throw new IllegalArgumentException(); //ResponseEntity.status(HttpStatus.CONFLICT).body("No seats available. Please try booking a different flight");
        }
    }
    public BoardingPassResponse makeBoardingPassResponseFromBoardingPass(Long boardingPassId){
        BoardingPass boardingPass = sqlRepository.findBoardingPassById(boardingPassId).get();
        BoardingPassResponse boardingPassResponse = new BoardingPassResponse(boardingPass);
        return boardingPassResponse;
    }

    public List<BoardingPass> getListOfBoardingPassesFromBoardingPassBookingRequest(BoardingPassBookingRequest boardingPassBookingRequest, Flight flight) {
        List<BoardingPass> boardingPasses = new ArrayList<>();
        List<Passenger> passengers = boardingPassBookingRequest.getPassengers();

        for (Passenger passenger : passengers) {
            BoardingPass boardingPass = new BoardingPass(passenger);
            boardingPass.setFlight(flight);
            boardingPasses.add(boardingPass);
        }
        return boardingPasses;
    }

    @Transactional
    public void bookingCancellation(Long boardingPassId) {
        BoardingPass boardingPass = sqlRepository.findBoardingPassById(boardingPassId).get();
        Long flightId = boardingPass.getFlight().getId();
        sqlRepository.deleteBoardingPassById(boardingPassId);
        sqlRepository.increaseSeatsAvailable(flightId);

//            BoardingPassData boardingPass1 = boardingPassDataRepository.findById(boardingPassId).get();
//
//            Long flightId = boardingPass1.getFlightData().getId();

//            sqlRepository.findBoardingPassById(boardingPassId)
//                    .ifPresent(boardingPass -> sqlRepository.delete(boardingPass));

        //BoardingPassData boardingPass = boardingPassDataRepository.findById(boardingPassId).get();
        // boardingPass1.getFlightData().getBoardingPassData().removeIf(bp -> boardingPassId.equals(bp.getBoardingPassId()));
        //boardingPassDataRepository.deleteById(boardingPassId);

        //Poprawna wersja Raf
        //BoardingPassData boardingPass1 = boardingPassDataRepository.findById(boardingPassId).get();
        // boardingPass1.getFlightData().getBoardingPassData().removeIf(bp -> boardingPassId.equals(bp.getBoardingPassId()));
        //boardingPassDataRepository.deleteById(boardingPassId);

//            BoardingPass boardingPass2 = sqlRepository.findBoardingPassById(boardingPassId).get();
//            boardingPass2.getFlight().get.removeIf(bp -> boardingPassId.equals(bp.getBoardingPassId()));
//            boardingPassDataRepository.deleteById(boardingPassId);


        //Long flightId = boardingPass1.getFlight().getId();
        //Long flightId = sqlRepository.findBoardingPassById(boardingPassId).get().getFlight().getId();
//        sqlRepository.findBoardingPassById(boardingPassId).ifPresent(boardingPass1 ->
//                sqlRepository.deleteBoardingPassById(boardingPassId));

//            Flight flight = sqlRepository.findById(flightId).get();
//            FlightState flightState = new FlightState(sqlRepository, flight);
//            flightState.nextAvailableSeat();
    }

//    public void deleteBoardingPass(Long boardingPassId) {
//        Long flightId = boardingPassDataRepository.findById(id).get().getFlightData().getId();
//        boardingPassDataRepository.findById(id).ifPresent(boardingPass ->
//                boardingPassDataRepository.deleteById(id));
////        Long flightId = boardingPassRepository.findById(id).ifPresent(boardingPass ->
////                boardingPass.get().getFlight().getId());
//        FlightData flightData = flightDataRepository.findById(flightId).get();
//        FlightState flightState = new FlightState(flightDataRepository, flightData);
//        flightState.nextAvailableSeat();
//    }

}
