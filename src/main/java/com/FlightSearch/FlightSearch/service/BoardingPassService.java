package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.*;
import com.FlightSearch.FlightSearch.repository.entities.BoardingPass;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.repository.sqlRepository.BoardingPassDataRepository;
import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightDataRepository;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BoardingPassService {
    private final BoardingPassDataRepository boardingPassDataRepository;
    private final FlightDataRepository flightDataRepository;
    private SqlRepository sqlRepository;

    public BoardingPassService(BoardingPassDataRepository boardingPassDataRepository, FlightDataRepository flightDataRepository, SqlRepository sqlRepository) {
        this.boardingPassDataRepository = boardingPassDataRepository;
        this.flightDataRepository = flightDataRepository;
        this.sqlRepository = sqlRepository;
    }

    public List<BoardingPassResponse> generateBoardingPassesForAllPassengers(BoardingPassBookingRequest boardingPassBookingRequest, Long flightId) {
        Flight flight = sqlRepository.findById(flightId).get();
        FlightState flightState = new FlightState(sqlRepository, flight);
        List<BoardingPass> listOfBoardingPassesFromRequest = getListOfBoardingPassesFromBoardingPassBookingRequest(boardingPassBookingRequest, flight);
        List<BoardingPass> boardingPasses = new ArrayList<>();
        List<BoardingPassResponse> boardingPassesResponse = new ArrayList<>();
        //BoardingPassResponse boardingPassResponse = new BoardingPassResponse();

        if (/*!flightState.availableSeatsIsFinished()*/flightState.getCurrentNumberOfSeatsAvailable() >= listOfBoardingPassesFromRequest.size()) {
            listOfBoardingPassesFromRequest.forEach(boardingPass -> {
                //BoardingPass result = new BoardingPass(passenger.getFirstName(), passenger.getLastName(), flight);
                Long boardingPassDataId = sqlRepository.saveBoardingPass(boardingPass).getBoardingPassId();
                flightState.nextOccupiedSeat();
                BoardingPassResponse boardingPassResponse = makeBoardingPassResponseFromBoardingPass(boardingPassDataId);
                //boardingPass.setBoardingPassId(boardingPassDataId);
                //BoardingPassResponse boardingPassResponse = new BoardingPassResponse(boardingPass);
                boardingPassesResponse.add(boardingPassResponse);
            });
//               for(int x = 0; x <= passengers.size(); x++){
//               flightState.nextOccupiedSeat();}
            return boardingPassesResponse; //ResponseEntity.ok(result);
        } else {
            throw new IllegalArgumentException(); //ResponseEntity.status(HttpStatus.CONFLICT).body("No seats available. Please try booking a different flight");
        }
    }
    public BoardingPassResponse makeBoardingPassResponseFromBoardingPass(Long boardingPassId){
        BoardingPass boardingPass = sqlRepository.findBoardingPassById(boardingPassId).get();
        //ifPresent(boardingPass -> new BoardingPassResponse(boardingPass));
        BoardingPassResponse boardingPassResponse = new BoardingPassResponse(boardingPass);
        return boardingPassResponse;
    }

    public List<BoardingPass> getListOfBoardingPassesFromBoardingPassBookingRequest(BoardingPassBookingRequest boardingPassBookingRequest, Flight flight) {
        List<BoardingPass> boardingPasses = new ArrayList<>();
        List<Passenger> passengers = boardingPassBookingRequest.getPassengers();
        Long defaultBoardingPassId = boardingPassBookingRequest.getBoardingPassId();

        for (Passenger passenger : passengers) {
            BoardingPass boardingPass = new BoardingPass(passenger);
            boardingPass.setFlight(flight);
            //sqlRepository.findById(flightId).ifPresent(flight -> boardingPass.setFlight(flight));
            boardingPasses.add(boardingPass);
        }
        return boardingPasses;
    }

        @Transactional
        public void bookingCancellation(Long boardingPassId) {

//            BoardingPassData boardingPass1 = boardingPassDataRepository.findById(boardingPassId).get();
//
//            Long flightId = boardingPass1.getFlightData().getId();

//            sqlRepository.findBoardingPassById(boardingPassId)
//                    .ifPresent(boardingPass -> sqlRepository.delete(boardingPass));


            BoardingPass boardingPass = sqlRepository.findBoardingPassById(boardingPassId).get();
            sqlRepository.deleteBoardingPassById(boardingPassId);
            Long flightId =boardingPass.getFlight().getId();
            sqlRepository.increaseSeatsAvailable(flightId);

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
