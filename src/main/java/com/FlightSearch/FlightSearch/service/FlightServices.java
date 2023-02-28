package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.data.entities.FlightData;
import com.FlightSearch.FlightSearch.data.repository.sqlRepository.AirportRepository;
import com.FlightSearch.FlightSearch.data.repository.sqlRepository.FlightRepository;
import com.FlightSearch.FlightSearch.model.Airport;
import com.FlightSearch.FlightSearch.model.Flight;
import com.FlightSearch.FlightSearch.model.FlightRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class FlightServices {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightServices(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public void addFlight(FlightRequest flightRequest){
        /*FlightData result;
        Flight flight = Flight.from(flightRequest);
        flight.setAirport(Airport.from(airportRepository.findByLocation(flight.getDepartureTo())));
        result = flightRepository.save(FlightData.from(flight));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);*/

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
