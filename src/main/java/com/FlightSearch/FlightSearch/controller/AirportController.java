package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.model.Airport;
import com.FlightSearch.FlightSearch.repository.sqlRepository.AirportRepository;
import com.FlightSearch.FlightSearch.service.AirportReader;
import com.FlightSearch.FlightSearch.service.AirportServices;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airport")
public class AirportController {
    private final AirportServices airportServices;
    private final AirportReader airportReader;
    private final AirportRepository airportRepository;

    public AirportController(AirportServices airportServices, AirportReader airportReader, AirportRepository airportRepository) {
        this.airportServices = airportServices;
        this.airportReader = airportReader;
        this.airportRepository = airportRepository;
    }

    @GetMapping("searchByIataCode")
    public boolean checkAirportExistsFromIataCode(@RequestParam(required = true) String iataCode) {
        return airportRepository.existsByIataCode(iataCode);
    }
    @GetMapping("searchByLocation")
    public Airport searchAirportByLocation(@RequestParam(required = true) String location) {
        return airportRepository.findByLocation(location);
    }
    @GetMapping("searchById/{id}")
    public ResponseEntity<?> searchAirportById(@PathVariable Integer id) {
        if (!airportRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airportRepository.findById(id));
    }

    @GetMapping("/addAirports")
    public String addAirports() {
        airportReader.saveAirportsDataFromList();
        return "Airports's been added";
    }
    @Transactional
    @PutMapping("/updateAirport/{id}")
    public ResponseEntity<?> updateAirport(@PathVariable int id, @RequestBody Airport source) {
        if (!airportRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            airportRepository.findById(id).ifPresent(airport-> {
                airport.updateAirport(source);
                airportRepository.save(airport);
            });
            return ResponseEntity.noContent().build();
        }
    }
}
