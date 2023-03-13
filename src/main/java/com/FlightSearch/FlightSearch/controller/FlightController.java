package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.model.FlightResponse;
import com.FlightSearch.FlightSearch.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.model.Trip;
import com.FlightSearch.FlightSearch.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    ResponseEntity<FlightResponse> postFlight(@RequestBody @Valid CreateFlightRequest flightRequest) {
        Long flightId = flightService.addFlight(flightRequest);
        FlightResponse result = flightService.makeFlightResponseFromFlight(flightId);
        //flightData.setAirportData(airportDataRepository.findByLocation(flightData.getDepartureTo()));
        return ResponseEntity.ok(result);
        //ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping("/match")
    ResponseEntity<List<List<FlightResponse>>> getMatchingFlights(@RequestBody @Valid final Trip trip) {

        if (trip.isReturnTrip() && trip.getReturnDepartureDate() == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (!trip.isReturnTrip() && trip.getReturnDepartureDate() != null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        List<List<FlightResponse>> matchingFlightsResponse = flightService.searchMatchingFlights(trip);
        return ResponseEntity.ok(matchingFlightsResponse);
    }
//    ResponseEntity<List<List<FlightData>>> getMatchingFlights(@RequestBody @Valid Trip trip) {
//        List<List<FlightData>> matchingFlights = new ArrayList<>();
//        if (trip.isReturnTrip() && trip.getReturnDepartureDate() == null) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        if (!trip.isReturnTrip() && trip.getReturnDepartureDate() != null) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        List<FlightData> flightsData = flightDataRepository.findMatch(trip.getDepartureTo(), trip.getArrivalTo(), trip.getDepartureDate(), trip.getNumberOfPassengers());
//        List<FlightData> returnFlightsData = flightDataRepository.findReturnMatch(trip.getArrivalTo(), trip.getDepartureTo(), trip.getReturnDepartureDate(),trip.getNumberOfPassengers());
//        matchingFlights.add(flightsData);
//        if (returnFlightsData.size() != 0) {
//            matchingFlights.add(returnFlightsData);
//        }
//        return ResponseEntity.ok(matchingFlights);
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
