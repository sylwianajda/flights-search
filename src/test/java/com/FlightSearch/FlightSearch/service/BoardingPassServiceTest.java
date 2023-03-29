package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.BoardingPassBookingRequest;
import com.FlightSearch.FlightSearch.model.BoardingPassResponse;
import com.FlightSearch.FlightSearch.model.Passenger;
import com.FlightSearch.FlightSearch.repository.entities.Airport;
import com.FlightSearch.FlightSearch.repository.entities.BoardingPass;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardingPassServiceTest {
    @Mock
    SqlRepository sqlRepository;

    @InjectMocks
    BoardingPassService boardingPassService;
    @Test
    void shouldMakeBoardingPassResponseFromBoardingPass() {
        //given
        Long boardingPassId = 5L;
        BoardingPass boardingPass = new BoardingPass(5L,"Sylwia", "Kajetan",
                new Flight(3L, "234", "WROCLAW", "LIVERPOOL",
                LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                BigDecimal.valueOf(30), 60, new Airport(101, "dsf", "koa",
                "kola", "lala", (double) 14L, (double) 15L)));
        when(sqlRepository.findBoardingPassById(anyLong())).thenReturn(Optional.of(boardingPass));
        //when
        BoardingPassResponse boardingPassResponse = boardingPassService.makeBoardingPassResponseFromBoardingPass(boardingPassId);
        //then
        assertThat(boardingPassResponse).isNotNull();
        assertThat(boardingPassResponse.getBoardingPassId()).isNotNull();
        assertEquals(5L, boardingPassResponse.getBoardingPassId());
        assertEquals("Sylwia", boardingPassResponse.getFirstName());
        assertEquals("Kajetan", boardingPassResponse.getLastName());
        assertEquals(60,boardingPassResponse.getFlight().getNumberOfSeatsAvailable());
        assertEquals(101, boardingPassResponse.getFlight().getAirport().getId());
    }
//    public BoardingPassResponse makeBoardingPassResponseFromBoardingPass(Long boardingPassId){
//        BoardingPass boardingPass = sqlRepository.findBoardingPassById(boardingPassId).get();
//        BoardingPassResponse boardingPassResponse = new BoardingPassResponse(boardingPass);
//        return boardingPassResponse;
//    }

    @Test
    void shouldReturnListOfBoardingPassesFromBoardingPassBookingRequest() {
        //given
        List<Passenger> passengers = new ArrayList<>();
        Passenger passengerOne = new Passenger("Anna","Waścia");
        Passenger passengerTwo = new Passenger("Lovly", "Bumet");
        passengers.add(passengerOne);
        passengers.add(passengerTwo);
                Flight flight = new Flight(3L, "234", "WROCLAW", "LIVERPOOL",
                        LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                        BigDecimal.valueOf(30), 60, new Airport(101, "dsf", "koa",
                        "kola", "lala", (double) 14L, (double) 15L));
        BoardingPassBookingRequest boardingPassBookingRequest = new BoardingPassBookingRequest(passengers);
        //when
        List<BoardingPass> listOfBoardingPassesFromBoardingPassBookingRequest =
                boardingPassService.getListOfBoardingPassesFromBoardingPassBookingRequest(boardingPassBookingRequest, flight);
        //then
        assertThat(listOfBoardingPassesFromBoardingPassBookingRequest).isNotNull();
        assertThat(listOfBoardingPassesFromBoardingPassBookingRequest.get(0).getBoardingPassId()).isEqualTo(0L);
        assertEquals(passengers.size(), listOfBoardingPassesFromBoardingPassBookingRequest.size());
        assertEquals(listOfBoardingPassesFromBoardingPassBookingRequest.get(0).getFlight(),flight);
        assertEquals(listOfBoardingPassesFromBoardingPassBookingRequest.get(1).getFlight(),flight);
    }
//    public List<BoardingPass> getListOfBoardingPassesFromBoardingPassBookingRequest(BoardingPassBookingRequest boardingPassBookingRequest, Flight flight) {
//        List<BoardingPass> boardingPasses = new ArrayList<>();
//        List<Passenger> passengers = boardingPassBookingRequest.getPassengers();
//
//        for (Passenger passenger : passengers) {
//            BoardingPass boardingPass = new BoardingPass(passenger);
//            boardingPass.setFlight(flight);
//            boardingPasses.add(boardingPass);
//        }
//        return boardingPasses;
//    }

@Test
void shouldReturnEmptyListOfBoardingPassesFromBoardingPassBookingRequest() {
    //given
    List<Passenger> passengers = new ArrayList<>();
    Flight flight = new Flight(3L, "234", "WROCLAW", "LIVERPOOL",
            LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
            BigDecimal.valueOf(30), 60, new Airport(101, "dsf", "koa",
            "kola", "lala", (double) 14L, (double) 15L));
    BoardingPassBookingRequest boardingPassBookingRequest = new BoardingPassBookingRequest(passengers);
    //when
    List<BoardingPass> listOfBoardingPassesFromBoardingPassBookingRequest =
            boardingPassService.getListOfBoardingPassesFromBoardingPassBookingRequest(boardingPassBookingRequest, flight);
    //then
    assertThat(listOfBoardingPassesFromBoardingPassBookingRequest).isEmpty();
    assertEquals(passengers.size(), listOfBoardingPassesFromBoardingPassBookingRequest.size());

}
    @Test
    void shouldGenerateBoardingPassesForAllPassengers() {
        //given
        List<Passenger> passengers = new ArrayList<>();
        Passenger passengerOne = new Passenger("Anna","Waścia");
        passengers.add(passengerOne);
        Flight flight = new Flight(3L, "234", "WROCLAW", "LIVERPOOL",
                LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                BigDecimal.valueOf(30), 60, new Airport(101, "dsf", "koa",
                "kola", "lala", (double) 14L, (double) 15L));
        Long flightId = flight.getId();
        BoardingPass boardingPass = new BoardingPass(6L,"Anna", "Waścia", flight);
        BoardingPassBookingRequest boardingPassBookingRequest = new BoardingPassBookingRequest(passengers);
        when(sqlRepository.findFlightById(anyLong())).thenReturn(Optional.of(flight));
        when(sqlRepository.getCurrentNumberOfSeatsAvailable(anyLong())).thenReturn(flight.getNumberOfSeatsAvailable());
        when(sqlRepository.saveBoardingPass(any(BoardingPass.class)).getBoardingPassId()).thenReturn(boardingPass.getBoardingPassId());
        //when(sqlRepository.decreaseSeatsAvailable(flightId)).then(flight.setNumberOfSeatsAvailable(flight.getNumberOfSeatsAvailable()-1));

        //when
        List<BoardingPassResponse> boardingPassResponses = boardingPassService.generateBoardingPassesForAllPassengers(boardingPassBookingRequest, flightId);
        //then
    }
//    public List<BoardingPassResponse> generateBoardingPassesForAllPassengers(BoardingPassBookingRequest boardingPassBookingRequest, Long flightId) {
//        Flight flight = sqlRepository.findFlightById(flightId).get();
//        List<BoardingPass> listOfBoardingPassesFromRequest = getListOfBoardingPassesFromBoardingPassBookingRequest(boardingPassBookingRequest, flight);
//        List<BoardingPassResponse> boardingPassesResponse = new ArrayList<>();
//        Integer numberOfSeatsAvailable = sqlRepository.getCurrentNumberOfSeatsAvailable(flightId);
//
//        if (numberOfSeatsAvailable >= listOfBoardingPassesFromRequest.size()) {
//            listOfBoardingPassesFromRequest.forEach(boardingPass -> {
//                Long boardingPassDataId = sqlRepository.saveBoardingPass(boardingPass).getBoardingPassId();
//                sqlRepository.decreaseSeatsAvailable(flightId);
//                BoardingPassResponse boardingPassResponse = makeBoardingPassResponseFromBoardingPass(boardingPassDataId);
//                boardingPassesResponse.add(boardingPassResponse);
//            });
//            return boardingPassesResponse;
//        } else {
//            throw new IllegalArgumentException(); //ResponseEntity.status(HttpStatus.CONFLICT).body("No seats available. Please try booking a different flight");
//        }
//    }
}