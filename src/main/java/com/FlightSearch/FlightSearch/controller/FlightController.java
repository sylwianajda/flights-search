package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.model.FlightResponse;
import com.FlightSearch.FlightSearch.repository.entities.FlightData;
import com.FlightSearch.FlightSearch.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.model.Trip;
import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightDataRepository;
import com.FlightSearch.FlightSearch.service.FlightServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightServices flightServices;
    private final FlightDataRepository flightDataRepository;


    public FlightController(FlightServices flightServices,
                            FlightDataRepository flightDataRepository) {
        this.flightServices = flightServices;

        this.flightDataRepository = flightDataRepository;

    }

    @PostMapping("/add")
    ResponseEntity<FlightResponse> postFlight(@RequestBody @Valid CreateFlightRequest flightRequest) {
        Long flightId = flightServices.addFlight(flightRequest);
        FlightResponse result = flightServices.makeFlightResponseFromFlight(flightId);
        //flightData.setAirportData(airportDataRepository.findByLocation(flightData.getDepartureTo()));
        return ResponseEntity.ok(result);
        //ResponseEntity.created(URI.create("/" + result.getId())).body(result);
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
        List<FlightData> flightsData = flightDataRepository.findMatch(trip.getDepartureTo(), trip.getArrivalTo(), trip.getDepartureDate(), trip.getNumberOfPassengers());
        List<FlightData> returnFlightsData = flightDataRepository.findReturnMatch(trip.getArrivalTo(), trip.getDepartureTo(), trip.getReturnDepartureDate(),trip.getNumberOfPassengers());
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
