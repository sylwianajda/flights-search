package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.controller.exceptions.IllegalExceptionProcessing;
import com.FlightSearch.FlightSearch.controller.model.AirportRequest;
import com.FlightSearch.FlightSearch.controller.model.AirportResponse;
import com.FlightSearch.FlightSearch.service.AirportReader;
import com.FlightSearch.FlightSearch.service.AirportService;
import com.FlightSearch.FlightSearch.service.model.Airport;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@IllegalExceptionProcessing
@RequestMapping("/airport")
public class AirportController {
    private final AirportService airportService;
    private final AirportReader airportReader;

    public AirportController(AirportService airportService, AirportReader airportReader) {
        this.airportService = airportService;
        this.airportReader = airportReader;
    }

    @GetMapping("searchByIataCode")
    public ResponseEntity<String> checkAirportExistsFromIataCode(@RequestParam(required = true) @NotEmpty String iataCode) {
        boolean isExistingAirportByIataCode = airportService.findExistingAirportByIataCode(iataCode);
        if (isExistingAirportByIataCode!=true){
            return ResponseEntity.status(404).body("Airport doesn't exist");
        }
        return ResponseEntity.status(200).body("Airport exist");

    }
    @GetMapping("searchByLocation")
    public  ResponseEntity<AirportResponse> searchAirportByName(@RequestParam(required = true) String name) {
        try {
            Airport airport = airportService.searchFlightsByName(name);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        Airport airport = airportService.searchFlightsByName(name);
        AirportResponse airportResponse = new AirportResponse(airport);
        return ResponseEntity.ok(airportResponse);
    }

    @GetMapping("searchById/{id}")
    public ResponseEntity<AirportResponse> searchAirportById(@PathVariable Integer id) {
        return ResponseEntity.ok(airportService.findAirportById(id));
    }

    @GetMapping("/addAirports")
    public ResponseEntity<String> addAirports() {
        boolean isSaved = airportReader.saveAirportsDataFromList();
        if (isSaved!=true){
            return ResponseEntity.status(500).body("Airports haven't been added");
        }
        return ResponseEntity.status(201).body("Airports have been added");
    }

    @PutMapping("/updateAirport/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable int id, @Valid @RequestBody AirportRequest source) {
        AirportResponse airportUpdated = airportService.executeAirportUpdate(id, source);
        if (airportUpdated != null) {
            return ResponseEntity.ok(airportUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
