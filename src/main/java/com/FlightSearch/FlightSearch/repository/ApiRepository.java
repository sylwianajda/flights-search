package com.FlightSearch.FlightSearch.repository;

import com.FlightSearch.FlightSearch.repository.entities.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ApiRepository {
    List<Airport> findAllAirports();

    List<Flight> findAllFlights();


    Optional<Airport> findById(Integer id);

    List<Airport> findByLocation(String location);

    Airport saveAirport(Airport entity);

    boolean existsByIataCode(String iataCode);

    Optional<Flight> findByDepartureTo(String departureTo);

    Optional<Flight> findById(Long id);

    Flight saveFlight(Flight entity);

    List<Flight> findAllByAirportId(Integer airportId);

    List<Flight> findMatch(String departureTo, String arrivalTo, LocalDateTime departureDate, int numberOfPassengers);

    List<Flight> findReturnMatch(String returnDepartureTo, String returnArrivalTo, LocalDateTime returnDepartureDate, int numberOfPassengers);
    BoardingPass save(BoardingPass entity);

}