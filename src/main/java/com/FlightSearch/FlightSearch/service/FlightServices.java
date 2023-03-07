package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.FlightResponse;
import com.FlightSearch.FlightSearch.repository.sqlRepository.AirportDataRepository;
import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightDataRepository;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightServices {
    private final FlightDataRepository flightDataRepository;
    private final AirportDataRepository airportDataRepository;

    private SqlRepository sqlRepository;

    public FlightServices(FlightDataRepository flightDataRepository, AirportDataRepository airportDataRepository, SqlRepository sqlRepository) {
        this.flightDataRepository = flightDataRepository;
        this.airportDataRepository = airportDataRepository;
        this.sqlRepository = sqlRepository;
    }

    public Long addFlight(CreateFlightRequest flightRequest){
        Flight flight = makeFlightFromCreateFlightRequest(flightRequest);
        Long flightId = sqlRepository.saveFlight(flight).getId();
       // Long flightId = flight.getId();
        //flight.setAirport(Airport.from(airportDataRepository.findByLocation(flight.getDepartureTo())));
        //return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
        //FlightResponse flightResponse = new FlightResponse(sqlRepository.findById(flightId).get());
        return flightId;
    }
    public FlightResponse makeFlightResponseFromFlight(Long flightId){
        Flight flight = sqlRepository.findById(flightId).get();
        FlightResponse flightResponse = new FlightResponse(flight);
        return flightResponse;
    }

    public Flight makeFlightFromCreateFlightRequest(CreateFlightRequest flightRequest){
        Flight flight = new Flight(flightRequest);
        sqlRepository.findById(flightRequest.getAirportId()).ifPresent(airport ->flight.setAirport(airport));
        return flight;
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
