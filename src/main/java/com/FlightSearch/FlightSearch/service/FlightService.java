package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.FlightResponse;
import com.FlightSearch.FlightSearch.model.Trip;
import com.FlightSearch.FlightSearch.model.WeightFlightsPath;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightService {
    private SqlRepository sqlRepository;
    private FlightChecker flightChecker;

    public FlightService(SqlRepository sqlRepository, FlightChecker flightChecker) {
        this.sqlRepository = sqlRepository;
        this.flightChecker = flightChecker;
    }
    @Transactional
    public Long addFlight(CreateFlightRequest flightRequest) {
        Flight flight = makeFlightFromCreateFlightRequest(flightRequest);
        Long flightId = sqlRepository.saveFlight(flight).getId();
        return flightId;
    }

    public FlightResponse makeFlightResponseFromFlight(Long flightId) {
        Flight flight = sqlRepository.findFlightById(flightId).get();
        FlightResponse flightResponse = new FlightResponse(flight);
        return flightResponse;
    }

    public Flight makeFlightFromCreateFlightRequest(CreateFlightRequest flightRequest) {
        Flight flight = new Flight(flightRequest);
        sqlRepository.findAirportById(flightRequest.getAirportId()).ifPresent(airport -> flight.setAirport(airport));
        return flight;
    }

    public List<List<FlightResponse>> searchMatchingFlights(Trip trip) {
        List<List<Flight>> matchingFlights = new ArrayList<>();
        List<Flight> flights = sqlRepository.findMatch(trip.getDepartureTo(), trip.getArrivalTo(), trip.getDepartureDate(), trip.getNumberOfPassengers());
        List<Flight> returnFlights = sqlRepository.findReturnMatch(trip.getArrivalTo(), trip.getDepartureTo(), trip.getReturnDepartureDate(), trip.getNumberOfPassengers());
        matchingFlights.add(flights);
        if (returnFlights.size() != 0) {
            matchingFlights.add(returnFlights);
        }
        return makeFlightsResponseFromFlights(matchingFlights);
    }

    public List<List<FlightResponse>> makeFlightsResponseFromFlights(List<List<Flight>> matchingFlights) {
        List<List<FlightResponse>> matchingFlightsResponse = matchingFlights.stream()
                .map(flightList -> flightList.stream()
                        .map(flight -> new FlightResponse(flight))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        return matchingFlightsResponse;
    }

    public List<FlightResponse> makeFlightsResponseFromListOfFlights(List<Flight> matchingFlights) {
        List<FlightResponse> matchingFlightsResponse = matchingFlights.stream()
                .map(flight -> new FlightResponse(flight))
                .collect(Collectors.toList());
        return matchingFlightsResponse;
    }
    @Transactional
    public void deleteOldFlights() {
        LocalDateTime weekBeforeNow = LocalDateTime.now().minus(1, ChronoUnit.WEEKS);
        List<Flight> allFlightsByArrivalDateOlderThanWeekBeforeNow = sqlRepository.findAllFlightsByArrivalDateOlderThanWeekBeforeNow(weekBeforeNow);
        for (Flight f:allFlightsByArrivalDateOlderThanWeekBeforeNow) {
            sqlRepository.removeFlightDataFromAirportDataInFlightsList(f.getId());
            sqlRepository.deleteFlightById(f.getId());
        }
    }

    public List<Flight> searchFlightsByAirportId(Integer airportId) {
        return sqlRepository.findFlightsByAirportId(airportId);
    }

    public List<List<FlightResponse>> searchMatchingFlightsWithStops(Trip trip) {
        List<List<Flight>> matchingFlights = new ArrayList<>();
        List<List<Flight>> returnMatchingFlights = new ArrayList<>();

        Integer airportStartId = sqlRepository.findAirportByName(trip.getDepartureTo()).getId();
        List<Flight> flightsByAirportStartId = sqlRepository.findFlightsByAirportId(airportStartId).stream()
                .filter(flight -> flight.getDepartureDate().isAfter(trip.getDepartureDate())).toList();
        for (Flight flight1: flightsByAirportStartId) {
            Integer airportMiddleId = sqlRepository.findAirportByName(flight1.getArrivalTo()).getId();
             sqlRepository.findFlightsByAirportId(airportMiddleId).stream()
                    .filter(flight2 -> flight2.getArrivalTo().contains(trip.getArrivalTo())
                            && flight2.getDepartureDate().isAfter(flight1.getArrivalDate().plusHours(2L))
                            && flight2.getDepartureDate().isBefore(flight1.getArrivalDate().plusDays(1L))).toList()
                    .forEach(f -> {
                        List<Flight> joinedFlights = new ArrayList<>();
                            joinedFlights.add(flight1);
                            joinedFlights.add(f);
                            matchingFlights.add(joinedFlights);
                            }
                    );
        }

        LocalDateTime returnDepartureDate = trip.getReturnDepartureDate();
        String returnDepartureTo = trip.getArrivalTo();
        String returnArrivalTo = trip.getDepartureTo();

        Integer returnAirportStartId = sqlRepository.findAirportByName(returnDepartureTo).getId();
        List<Flight> returnFlightsByAirportStartId = sqlRepository.findFlightsByAirportId(returnAirportStartId).stream()
                .filter(flight -> flight.getDepartureDate().isAfter(returnDepartureDate)).toList();
        for (Flight flight1: returnFlightsByAirportStartId) {
            Integer returnAirportMiddleId = sqlRepository.findAirportByName(flight1.getArrivalTo()).getId();
            sqlRepository.findFlightsByAirportId(returnAirportMiddleId).stream()
                    .filter(flight2 -> flight2.getArrivalTo().contains(returnArrivalTo)
//
                            && flight2.getDepartureDate().isAfter(flight1.getArrivalDate().plusHours(2L))
                            && flight2.getDepartureDate().isBefore(flight1.getArrivalDate().plusDays(1L))).toList()
                    .forEach(f -> {
                                List<Flight> joinedFlights = new ArrayList<>();
                                joinedFlights.add(flight1);
                                joinedFlights.add(f);
                                returnMatchingFlights.add(joinedFlights);
                            }
                    );
        }

        return makeFlightsResponseFromFlights(returnMatchingFlights);
    }

    public List<String> searchConnections(Trip trip) {
        List<Flight> directFlightsToDestination = sqlRepository.findMatch(trip.getDepartureTo(), trip.getArrivalTo(), trip.getDepartureDate(), trip.getNumberOfPassengers());
        List<Flight> directReturnFlights = sqlRepository.findReturnMatch(trip.getArrivalTo(), trip.getDepartureTo(), trip.getReturnDepartureDate(), trip.getNumberOfPassengers());

        List<List<Flight>> flightsWithStopsToDestination = findMatchingFlightsWithStops(trip);
        List<List<Flight>> ReturnFlightsWithStopsToDestination = findReturnMatchingFlightsWithStops(trip);

        List<Flight> flightsToGraph = flightsWithStopsToDestination.stream().flatMap(List::stream).collect(Collectors.toList());
        flightsToGraph.addAll(directFlightsToDestination);


        List<WeightFlightsPath> costOfFlightsWithStops = new ArrayList<>();
        List<WeightFlightsPath> costOfDirectFlights = new ArrayList<>();

        flightsWithStopsToDestination.stream().forEach(
                flights -> {

                    for (int i = 0; i < flights.size(); i++) {
                        List<Long> joinedIds = new ArrayList<>();
                        List<BigDecimal> joinedPrices = new ArrayList<>();
                        flights.stream().forEach(flight -> {
                            joinedPrices.add(flight.getPrice());
                            joinedIds.add(flight.getId());
                        });

                        if (flights.size() - 1 == i) {
                            int sumedPrices = 0;
                            for (BigDecimal price : joinedPrices) {
                                sumedPrices += price.intValue();
                            }
                            BigDecimal sumedPricesInBD = new BigDecimal(sumedPrices);
                            WeightFlightsPath weightFlightsPath = new WeightFlightsPath(joinedIds, sumedPricesInBD);
                            costOfFlightsWithStops.add(weightFlightsPath);
                        }
                    }
                }
        );

        directFlightsToDestination.stream().forEach(
                flight -> {
                    List<Long> ids = new ArrayList<>();
                        ids.add(flight.getId());
                        BigDecimal price= flight.getPrice();
                        WeightFlightsPath weightFlightsPath = new WeightFlightsPath(ids, price);
                        costOfDirectFlights.add(weightFlightsPath);
                }
        );

        List<WeightFlightsPath> costOfFlights = Stream.concat(costOfDirectFlights.stream(), costOfFlightsWithStops.stream()).toList();


        List<String> shortestPath = flightChecker.findShortestPath(trip.getDepartureTo(), trip.getArrivalTo(), flightsToGraph);

        List<String> shortestPathUla = flightChecker.findShortestPathUla(trip.getDepartureTo(), trip.getArrivalTo(), costOfFlights);
        return shortestPathUla;
    }

    public  List<List<Flight>> findMatchingFlightsWithStops(Trip trip) {
        List<List<Flight>> matchingFlights = new ArrayList<>();

        Integer airportStartId = sqlRepository.findAirportByName(trip.getDepartureTo()).getId();
        List<Flight> flightsByAirportStartId = sqlRepository.findFlightsByAirportId(airportStartId).stream()
                .filter(flight -> flight.getDepartureDate().isAfter(trip.getDepartureDate())).toList();
        for (Flight flight1 : flightsByAirportStartId) {
            Integer airportMiddleId = sqlRepository.findAirportByName(flight1.getArrivalTo()).getId();
            sqlRepository.findFlightsByAirportId(airportMiddleId).stream()
                    .filter(flight2 -> flight2.getArrivalTo().contains(trip.getArrivalTo())
                            && flight2.getDepartureDate().isAfter(flight1.getArrivalDate().plusHours(2L))
                            && flight2.getDepartureDate().isBefore(flight1.getArrivalDate().plusDays(1L))).toList()
                    .forEach(f -> {
                                List<Flight> joinedFlights = new ArrayList<>();
                                joinedFlights.add(flight1);
                                joinedFlights.add(f);
                                matchingFlights.add(joinedFlights);
                            }
                    );
        }
        return matchingFlights;
    }

    public  List<List<Flight>> findReturnMatchingFlightsWithStops(Trip trip) {
        List<List<Flight>> returnMatchingFlights = new ArrayList<>();
        LocalDateTime returnDepartureDate = trip.getReturnDepartureDate();
        String returnDepartureTo = trip.getArrivalTo();
        String returnArrivalTo = trip.getDepartureTo();

        Integer returnAirportStartId = sqlRepository.findAirportByName(returnDepartureTo).getId();
        List<Flight> returnFlightsByAirportStartId = sqlRepository.findFlightsByAirportId(returnAirportStartId).stream()
                .filter(flight -> flight.getDepartureDate().isAfter(returnDepartureDate)).toList();
        for (Flight flight1 : returnFlightsByAirportStartId) {
            Integer returnAirportMiddleId = sqlRepository.findAirportByName(flight1.getArrivalTo()).getId();
            sqlRepository.findFlightsByAirportId(returnAirportMiddleId).stream()
                    .filter(flight2 -> flight2.getArrivalTo().contains(returnArrivalTo)
                            && flight2.getDepartureDate().isAfter(flight1.getArrivalDate().plusHours(2L))
                            && flight2.getDepartureDate().isBefore(flight1.getArrivalDate().plusDays(1L))).toList()
                    .forEach(f -> {
                                List<Flight> joinedFlights = new ArrayList<>();
                                joinedFlights.add(flight1);
                                joinedFlights.add(f);
                                returnMatchingFlights.add(joinedFlights);
                            }
                    );
        }

        return returnMatchingFlights;
    }
}
