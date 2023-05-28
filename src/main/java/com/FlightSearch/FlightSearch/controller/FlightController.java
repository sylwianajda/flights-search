package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.controller.model.FlightResponse;
import com.FlightSearch.FlightSearch.controller.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.controller.model.Trip;
import com.FlightSearch.FlightSearch.service.model.Flight;
import com.FlightSearch.FlightSearch.service.FlightService;
import org.springframework.http.*;
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
        return ResponseEntity.ok(result);
    }

    @GetMapping("/matchWithoutStops")
    ResponseEntity<List<List<FlightResponse>>> getMatchingFlightsWithoutStops(@RequestBody @Valid final Trip trip) {
        boolean validationResponse = getValidation(trip);
        if (validationResponse != true) {
            return ResponseEntity.unprocessableEntity().build();
        }
        List<List<FlightResponse>> matchingFlightsResponse = flightService.searchMatchingFlights(trip);
        return ResponseEntity.ok(matchingFlightsResponse);
    }


    private static boolean getValidation(Trip trip) {
        if (trip.isReturnTrip() && trip.getReturnDepartureDate() == null) {
            return false;
        }
        if (!trip.isReturnTrip() && trip.getReturnDepartureDate() != null) {
            return false;
        }
        return true;
    }

    @PostMapping("/getFlighFromExternalAPI")
    public Object getFlightFromExternalAPI(@RequestBody @Valid final Trip trip) {
        boolean validationResponse = getValidation(trip);
        if (validationResponse != true) {
            return ResponseEntity.unprocessableEntity().build();
        }
        ResponseEntity<String> response = flightService.getFlightFromExternalApi(trip);
        return response.getBody();
    }


    @GetMapping("/matchWithStops") // TODO:xxx
    ResponseEntity<List<List<FlightResponse>>> getMatchingFlightsWithStops(@RequestBody @Valid final Trip trip) {
        boolean validationResponse = getValidation(trip);
        if (validationResponse != true) {
            return ResponseEntity.unprocessableEntity().build();
        }
        List<List<FlightResponse>> matchingFlightsResponse = flightService.searchMatchingFlightsWithStops(trip);
        return ResponseEntity.ok(matchingFlightsResponse);
    }

    @GetMapping("/getFlightsByAirportId/{airportId}")
    ResponseEntity<List<Flight>> getFlightsByAirportId(@PathVariable final Integer airportId) {
        List<Flight> matchingFlights = flightService.searchFlightsByAirportId(airportId);
        return ResponseEntity.ok(matchingFlights);
    }

    @GetMapping("/match")
    ResponseEntity<List<String>> getMatchingFlights(@RequestBody @Valid final Trip trip) {
        boolean validationResponse = getValidation(trip);
        if (validationResponse != true) {
            return ResponseEntity.unprocessableEntity().build();
        }
      //return ResponseEntity.ok(flightService.searchConnections(trip));
        return null;
    }
}
