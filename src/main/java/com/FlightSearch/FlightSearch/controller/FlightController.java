package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.controller.model.FlightResponse;
import com.FlightSearch.FlightSearch.controller.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.controller.model.MergeFlightResponse;
import com.FlightSearch.FlightSearch.service.model.Trip;
import com.FlightSearch.FlightSearch.service.model.Flight;
import com.FlightSearch.FlightSearch.service.FlightService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/flight")
@OpenAPIDefinition(info=@Info(title="Flights search"))
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @Operation(summary="Allows to add a flight")
    @PostMapping("/add")
    ResponseEntity<FlightResponse> postFlight(@RequestBody @Valid CreateFlightRequest flightRequest) {
        Long flightId = flightService.addFlight(flightRequest);
        FlightResponse result = flightService.makeFlightResponseFromFlight(flightId);
        return ResponseEntity.ok(result);
    }
    @Operation(summary="Return direct flights in one way or both sides")
    @Parameters({
            @Parameter(name = "departureTo", description = "The name of airport from which the journey will start")
    })
    @GetMapping("/matchWithoutStops")
    ResponseEntity<List<List<FlightResponse>>> getMatchingFlightsWithoutStops(@RequestParam(required = true) @NotBlank String departureTo,
                                                                              @RequestParam(required = true) @NotBlank String arrivalTo,
                                                                              @RequestParam(required = true) @NotBlank LocalDateTime departureDate,
                                                                              @RequestParam(required = true) @NotBlank Boolean returnTrip,
                                                                              @RequestParam(required = false) @NotBlank LocalDateTime returnDepartureDate,
                                                                              @RequestParam(required = true) @NotBlank int numberOfPassengers) {

        Trip trip = new Trip(departureTo, arrivalTo, departureDate, returnTrip, returnDepartureDate, numberOfPassengers);
        boolean validationRequest = getValidation(trip);
        if (validationRequest != true) {
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
    public Object getFlightFromExternalAPI(@RequestParam(required = true) @NotBlank String departureTo,
                                           @RequestParam(required = true) @NotBlank String arrivalTo,
                                           @RequestParam(required = true) @NotBlank LocalDateTime departureDate,
                                           @RequestParam(required = true) @NotBlank Boolean returnTrip,
                                           @RequestParam(required = false) @NotBlank LocalDateTime returnDepartureDate,
                                           @RequestParam(required = true) @NotBlank int numberOfPassengers) {

        Trip trip = new Trip(departureTo, arrivalTo, departureDate, returnTrip, returnDepartureDate, numberOfPassengers);
        boolean validationRequest = getValidation(trip);
        if (validationRequest != true) {
            return ResponseEntity.unprocessableEntity().build();
        }
        ResponseEntity<String> response = flightService.getFlightFromExternalApi(trip);
        return response.getBody();
    }


    @GetMapping("/matchWithStops")
    ResponseEntity<List<List<FlightResponse>>> getMatchingFlightsWithStops(@RequestParam(required = true) @NotBlank String departureTo,
                                                                           @RequestParam(required = true) @NotBlank String arrivalTo,
                                                                           @RequestParam(required = true) @NotBlank LocalDateTime departureDate,
                                                                           @RequestParam(required = true) @NotBlank Boolean returnTrip,
                                                                           @RequestParam(required = false) @NotBlank LocalDateTime returnDepartureDate,
                                                                           @RequestParam(required = true) @NotBlank int numberOfPassengers) {

        Trip trip = new Trip(departureTo, arrivalTo, departureDate, returnTrip, returnDepartureDate, numberOfPassengers);
        boolean validationRequest = getValidation(trip);
        if (validationRequest != true) {
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
    ResponseEntity<MergeFlightResponse> getMatchingFlights(@RequestParam(required = true) @NotBlank String departureTo,
                                                           @RequestParam(required = true) @NotBlank String arrivalTo,
                                                           @RequestParam(required = true) @NotBlank LocalDateTime departureDate,
                                                           @RequestParam(required = true) @NotBlank Boolean returnTrip,
                                                           @RequestParam(required = false) @NotBlank LocalDateTime returnDepartureDate,
                                                           @RequestParam(required = true) @NotBlank int numberOfPassengers) {

        Trip trip = new Trip(departureTo, arrivalTo, departureDate, returnTrip, returnDepartureDate, numberOfPassengers);
        boolean validationResponse = getValidation(trip);
        if (validationResponse != true) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(flightService.makeMergeFlightResponseFromMapWithConnections(flightService.searchConnections(trip)));
    }
}
