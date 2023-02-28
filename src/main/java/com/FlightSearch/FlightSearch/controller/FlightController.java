package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.data.entities.FlightData;
import com.FlightSearch.FlightSearch.model.Trip;
import com.FlightSearch.FlightSearch.data.repository.sqlRepository.AirportRepository;
import com.FlightSearch.FlightSearch.data.repository.sqlRepository.FlightRepository;
import com.FlightSearch.FlightSearch.service.AirportServices;
import com.FlightSearch.FlightSearch.service.FlightServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightServices flightServices;
    private final AirportServices airportServices;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightController(FlightServices flightServices, AirportServices airportServices,
                            FlightRepository flightRepository,
                            AirportRepository airportRepository) {
        this.flightServices = flightServices;
        this.airportServices = airportServices;
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }
    @PostMapping("/add")
    ResponseEntity<FlightData> postFlight(@RequestBody @Valid FlightData flightData) {
        FlightData result;
        flightData.setAirportData(airportRepository.findByLocation(flightData.getDepartureTo()));
        result = flightRepository.save(flightData);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping("/match")
    ResponseEntity<List<List<FlightData>>> getMatchingFlights(@RequestBody @Valid Trip trip) {
        List<List<FlightData>> matchingFlights = new ArrayList<>();
        if (trip.isReturnTrip() && trip.getReturnDepartureDate() == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (!trip.isReturnTrip() && trip.getReturnDepartureDate() != null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        List<FlightData> flightsData = flightRepository.findMatch(trip.getDepartureTo(), trip.getArrivalTo(), trip.getDepartureDate(), trip.getNumberOfPassengers());
        List<FlightData> returnFlightsData = flightRepository.findReturnMatch(trip.getArrivalTo(), trip.getDepartureTo(), trip.getReturnDepartureDate(),trip.getNumberOfPassengers());
        matchingFlights.add(flightsData);
        if (returnFlightsData.size() != 0) {
            matchingFlights.add(returnFlightsData);
        }
        return ResponseEntity.ok(matchingFlights);
    }

//    @GetMapping ("/matching")
//    @RequestParameterValidation
//    ResponseEntity<List<Flight>> findMatchingFlights(@RequestParam (required = true) String departureFrom,
//                                                     @RequestParam (required = true) String arrivalTo,
//                                                     @RequestParam (required = true) LocalDateTime departDate,
//                                                     @RequestParam (required = true) Integer numberOfPassengers,
//                                                     @RequestParam (required = true) boolean returnTrip,
//                                                     @RequestParam (required = false) LocalDateTime returnDepartDate) {
//
//        if (returnTrip && returnDepartDate != null) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        List<Flight> flights =flightRepository.findMatch(departureFrom,arrivalTo,departDate);
//        return ResponseEntity.ok(flights);
//    }
//        Integer airportId = airportServices.airportFinderByDepartureFrom(trip.getDepartureFrom());
//        List<Flight> flightsDepartureAirport = flightRepository.findAllByAirportId(airportId);
//        List<Flight> flightsFromDepartureAirportToArrivalAirport = new ArrayList<>();
//        for (Flight f : flightsDepartureAirport) {
//            int n;
//            if (f.getArrivalTo().equals(trip.getArrivalTo()) && (f.getDepartDate().toLocalDate().equals(trip.getDepartDate().toLocalDate()){
//                flightsFromDepartureAirportToArrivalAirport.add(f);
//            } else if (f.getArrivalTo().equals(trip.getArrivalTo()) && (f.getDepartDate().toLocalDate().equals(trip.getDepartDate().toLocalDate().plusDays(n)))){
//
//            }
//
//            }
//                int n;
//            do {
//                || trip.getDepartDate().toLocalDate().
//                date.plusDays(1);
//                n++;
//            } while date.get;))
//        return ResponseEntity.ok(flightsFromDepartureAirportToArrivalAirport);
//    }
//}
}
