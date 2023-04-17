package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.model.FlightResponse;
import com.FlightSearch.FlightSearch.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.model.Trip;
import com.FlightSearch.FlightSearch.repository.entities.BoardingPass;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.service.FlightService;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


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
    @PostMapping("/getFlighFromExternalAPI")
    public Object getFlightFromExternalAPI(@RequestBody @Valid final Trip trip){
        if (trip.isReturnTrip() && trip.getReturnDepartureDate() == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (!trip.isReturnTrip() && trip.getReturnDepartureDate() != null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", "109353c6-6432-4acf-8e77-ef842f64a664");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Trip> entity = new HttpEntity<>(trip, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8084/getFlight", HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
    @Scheduled(fixedDelay = 1140,initialDelay = 1,timeUnit = TimeUnit.MINUTES )
    @DeleteMapping("/deleteOldFlight")
    void deleteFlightOlderThanWeek() {
        flightService.deleteOldFlights();
    }
    @GetMapping("/matchWithStops") // TODO:kodifhyg
    ResponseEntity<List<List<FlightResponse>>> getMatchingFlightsWithStops(@RequestBody @Valid final Trip trip) {

        if (trip.isReturnTrip() && trip.getReturnDepartureDate() == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        if (!trip.isReturnTrip() && trip.getReturnDepartureDate() != null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        List<List<FlightResponse>> matchingFlightsResponse = flightService.searchMatchingFlightsWithStops(trip);
        return ResponseEntity.ok(matchingFlightsResponse);
    }

    @GetMapping("/getFlightsByAirportId/{airportId}")
    ResponseEntity<List<Flight>> getFlightsByAirportId(@PathVariable final Integer airportId) {

//        if (trip.isReturnTrip() && trip.getReturnDepartureDate() == null) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        if (!trip.isReturnTrip() && trip.getReturnDepartureDate() != null) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
        List<Flight> matchingFlights = flightService.searchFlightsByAirportId(airportId);
        return ResponseEntity.ok(matchingFlights);
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
