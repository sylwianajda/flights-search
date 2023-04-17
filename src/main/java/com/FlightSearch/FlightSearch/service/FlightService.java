package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.FlightResponse;
import com.FlightSearch.FlightSearch.model.Trip;
import com.FlightSearch.FlightSearch.repository.entities.Airport;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightDataRepository;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class FlightService {
    private SqlRepository sqlRepository;
    private FlightDataRepository flightDataRepository;

    public FlightService(SqlRepository sqlRepository, FlightDataRepository flightDataRepository) {
        this.sqlRepository = sqlRepository;
        this.flightDataRepository = flightDataRepository;
    }

    public Long addFlight(CreateFlightRequest flightRequest) {
        Flight flight = makeFlightFromCreateFlightRequest(flightRequest);
        Long flightId = sqlRepository.saveFlight(flight).getId();
        // Long flightId = flight.getId();
        //flight.setAirport(Airport.from(airportDataRepository.findByLocation(flight.getDepartureTo())));
        //return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        //FlightResponse flightResponse = new FlightResponse(sqlRepository.findById(flightId).get());
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
            //flightDataRepository.findById(f.getId()).get().getAirportData().getFlightsData().removeIf(flightData -> flightData.getId() == f.getId());
            sqlRepository.deleteFlightById(f.getId());
        }
    }

    public List<Flight> searchFlightsByAirportId(Integer airportId) {
        //Integer airportId = sqlRepository.findByDepartureTo(trip.getDepartureTo()).get().getAirport().getId();
        return sqlRepository.findFlightsByAirportId(airportId);

    }

    public List<List<FlightResponse>> searchMatchingFlightsWithStops(Trip trip) {
        List<List<Flight>> matchingFlights = new ArrayList<>();
        //List<Flight> joinedFlights = new ArrayList<>();

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
        //matchingFlights.add(joinedFlights);
        //matchingFlights.stream().sorted(Comparator.comparing(flight -> flight.))

        return makeFlightsResponseFromFlights(matchingFlights);
    }

//    public FlightDto createFlightDto(Flight flight){
//        var result = new FlightDto();
//        result.setDescription(description);
//        result.setTasks(tasks.stream().map(source ->source.toTask(result))
//                .collect(Collectors.toSet()));
//        result.setProject(project);
//        return result;
//    }
//    public FlightDto createFlightDto(Flight flight) {
//        FlightDto result = taskGroupRepository.save(source.toGroup(project));
//        return new FlightDto(result);
//    }


}
