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
    public Airport findAirportByName(String name) {
        return new Airport(airportDataRepository.findByName(name));
    }

    @Override
    public Optional<Airport> findAirportById(Integer id) {
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
    public Optional<Flight>  findByDepartureTo(String departureTo) {
        Optional<Flight> flight = Optional.of(new Flight(flightDataRepository.findByDepartureTo(departureTo).get()));
        return flight;
    }

    public Optional<Flight> findFlightById(Long id) {
        Optional<Flight> flight = Optional.of(new Flight(flightDataRepository.findById(id).get()));
        return flight;
    }

    @Override
    public Flight saveFlight(Flight entity) {
        final FlightData flightData = new FlightData(entity);
        return new Flight(flightDataRepository.save(flightData));
    }

//    @Override
//    public List<Flight> findAllByAirportId(Integer airportId) {
//        List<Flight> flights = flightDataRepository.findAllByAirportId(airportId).stream()
//                .map(flightData ->  new Flight(flightData))
//                .collect(Collectors.toList());
//        return flights;
//    }

    @Override
    public List<Flight> findMatch(String departureTo, String arrivalTo, LocalDateTime departureDate, int numberOfPassengers) {
        List<Flight> flights = flightDataRepository.findMatch(departureTo, arrivalTo, departureDate, numberOfPassengers).stream()
                .map(flightData -> new Flight(flightData))
                .collect(Collectors.toList());
        return flights;
    }


    @Override
    public List<Flight> findReturnMatch(String returnDepartureTo, String returnArrivalTo, LocalDateTime returnDepartureDate, int numberOfPassengers) {
        List<Flight> flights = flightDataRepository.findReturnMatch(returnDepartureTo, returnArrivalTo, returnDepartureDate, numberOfPassengers).stream()
                .map(flightData -> new Flight(flightData))
                .collect(Collectors.toList());
        return flights;
    }
    @Override
    public BoardingPass saveBoardingPass(BoardingPass entity) {
        final BoardingPassData boardingPassData = new BoardingPassData(entity);
        return new BoardingPass(boardingPassDataRepository.save(boardingPassData));

    }

    @Override
    public Optional<BoardingPass> findBoardingPassById(Long id) {
        Optional<BoardingPass> boardingPass = Optional.of(new BoardingPass(boardingPassDataRepository.findById(id).get()));
        return boardingPass;
    }

    @Override
    public void deleteBoardingPassById(Long id) {
        //final BoardingPassData boardingPassData = new BoardingPassData(entity);
        //flightDataRepository.findById(id).get()
        boardingPassDataRepository.deleteById(id);

    }

    @Override
    public void deleteBoardingPass(BoardingPass boardingPass) {
        //final BoardingPassData boardingPassData = new BoardingPassData(boardingPass);
        boardingPassDataRepository.delete(boardingPassDataRepository.getReferenceById(boardingPass.getBoardingPassId()));
    }

    @Override
    public void increaseSeatsAvailable(Long flightId) {
        flightDataRepository.increaseSeatsAvailable(flightId);
    }

    @Override
    public void decreaseSeatsAvailable(Long flightId/*, Integer numberOfSeatsAfterBookingBoardingPass*/) {
    flightDataRepository.decreaseSeatsAvailable(flightId /*, numberOfSeatsAfterBookingBoardingPass*/);
    }

    @Override
    public Integer getCurrentNumberOfSeatsAvailable(Long flightId) {
        return flightDataRepository.getCurrentNumberOfSeatsAvailable(flightId);
    }

//    @Override
//    public void deleteFlight(Flight flight) {
//        flightDataRepository.deleteFlight(new FlightData(flight));
//    }

    @Override
    public List<Flight> findAllFlightsByArrivalDateOlderThanWeekBeforeNow(LocalDateTime weekBeforeNow) {
        List<Flight> flights = flightDataRepository.findAllFlightsByArrivalDateOlderThanWeekBeforeNow(weekBeforeNow).stream()
                .map(flightData -> new Flight(flightData))
                .collect(Collectors.toList());
        return flights;
    }

    @Override
    public void removeFlightDataFromAirportDataInFlightsList(Long flightId) {
        flightDataRepository.findById(flightId)
                .ifPresent(flightData -> flightData.getAirportData().getFlightsData()
                        .removeIf(flightData1 -> flightData1.getId() == flightId));
    }

//    @Override
//    public List<Flight> findMatchWithStops(String departureTo, String arrivalTo, LocalDateTime departureDate, int numberOfPassengers) {
//        List<Flight> flights = flightDataRepository.findMatchWithStops(departureTo, arrivalTo, departureDate, numberOfPassengers).stream()
//                .map(flightData -> new Flight(flightData))
//                .collect(Collectors.toList());
//        return flights;
//    }

    @Override
    public List<Flight> findFlightsByAirportId(Integer airportId) {
        return flightDataRepository.findFlightsByAirportDataId(airportId).stream().map(flightData -> new Flight(flightData))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFlightById(Long id) {
        flightDataRepository.deleteById(id);
    }
}
