package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.FlightResponse;
import com.FlightSearch.FlightSearch.model.Trip;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private SqlRepository sqlRepository;

    public FlightService(SqlRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
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
