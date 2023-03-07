package com.FlightSearch.FlightSearch.repository;

import com.FlightSearch.FlightSearch.repository.entities.*;

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

    Optional<FlightData> findByDepartureTo();

    Optional<Flight> findById(Long id);

    Flight saveFlight(Flight entity);

    List<FlightData> findAllByAirportId(Integer airportId);

    List<FlightData> findMatch(String departureTo, String arrivalTo, LocalDateTime departureDate, int numberOfPassengers);

    List<FlightData> findReturnMatch();
    BoardingPassData save();

}