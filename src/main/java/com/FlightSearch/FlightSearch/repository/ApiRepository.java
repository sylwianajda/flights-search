package com.FlightSearch.FlightSearch.repository;

import com.FlightSearch.FlightSearch.repository.entities.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ApiRepository {
    List<Airport> findAllAirports();

    List<Flight> findAllFlights();


    Optional<Airport> findAirportById(Integer id);

    List<Airport> findByLocation(String location);

    Airport saveAirport(Airport entity);

    boolean existsByIataCode(String iataCode);

    Optional<Flight> findByDepartureTo(String departureTo);

    Optional<Flight> findFlightById(Long id);

    Flight saveFlight(Flight entity);

    //List<Flight> findAllByAirportId(Integer id);

    List<Flight> findMatch(String departureTo, String arrivalTo, LocalDateTime departureDate, int numberOfPassengers);

    List<Flight> findReturnMatch(String returnDepartureTo, String returnArrivalTo, LocalDateTime returnDepartureDate, int numberOfPassengers);
    BoardingPass saveBoardingPass(BoardingPass entity);

    Optional<BoardingPass> findBoardingPassById(Long id);

    void deleteBoardingPassById(Long id);

    void delete(BoardingPass boardingPass);
    void increaseSeatsAvailable(Long flightId);
    void decreaseSeatsAvailable(Long flightId);
    Integer getCurrentNumberOfSeatsAvailable(Long flightIdInteger);

}