package com.FlightSearch.FlightSearch.repository.sqlRepository;

import com.FlightSearch.FlightSearch.repository.entities.*;
import com.FlightSearch.FlightSearch.repository.ApiRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SqlRepository implements ApiRepository {
    private AirportDataRepository airportDataRepository;
    private FlightDataRepository flightDataRepository;
    private BoardingPassDataRepository boardingPassDataRepository;

    public SqlRepository(AirportDataRepository airportDataRepository, FlightDataRepository flightDataRepository, BoardingPassDataRepository boardingPassDataRepository) {
        this.airportDataRepository = airportDataRepository;
        this.flightDataRepository = flightDataRepository;
        this.boardingPassDataRepository = boardingPassDataRepository;
    }


    @Override
    public List<Airport> findAllAirports() {
        List<Airport> airports = airportDataRepository.findAll().stream()
                        .map(airportData -> new Airport(airportData))
                        .collect(Collectors.toList());
        return airports;
    }

    @Override
    public List<Flight> findAllFlights() {
        List<Flight> flights = flightDataRepository.findAll().stream()
                .map(flightData -> new Flight(flightData))
                .collect(Collectors.toList());
        return flights;
    }

    @Override
    public Optional<Airport> findById(Integer id) {
        //Optional<Airport> airport = Optional.of(Airport.from(airportDataRepository.findById(id).get()));
        Optional<Airport> airport = Optional.of(new Airport(airportDataRepository.findById(id).get()));
        return airport;
    }

    @Override
    public List<Airport> findByLocation(String location) {
        List<Airport> airports = airportDataRepository.findByLocation(location).stream()
                .map(airportData -> new Airport(airportData))
                .collect(Collectors.toList());

        return airports;
    }

    @Override
    public Airport saveAirport(Airport entity) {
        final AirportData airportData = new AirportData(entity);
        return new Airport(airportDataRepository.save(airportData));
    }


    @Override
    public boolean existsByIataCode(String iataCode) {
        return airportDataRepository.existsByIataCode(iataCode);
    }

    @Override
    public Optional<FlightData> findByDepartureTo() {
        return Optional.empty();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        Optional<Flight> flight = Optional.of(new Flight(flightDataRepository.findById(id).get()));
        return flight;
    }

    @Override
    public Flight saveFlight(Flight entity) {
        final FlightData flightData = new FlightData(entity);
        return new Flight(flightDataRepository.save(flightData));
    }

    @Override
    public List<FlightData> findAllByAirportId(Integer airportId) {
        return null;
    }

    @Override
    public List<FlightData> findMatch(String departureTo, String arrivalTo, LocalDateTime departureDate, int numberOfPassengers) {
        return flightDataRepository.findMatch(departureTo, arrivalTo, departureDate, numberOfPassengers);
    }


    @Override
    public List<FlightData> findReturnMatch() {
        return null;
    }
    @Override
    public BoardingPassData save() {
        return null;
    }
}
