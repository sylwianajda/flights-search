package com.FlightSearch.FlightSearch.controller;

import com.FlightSearch.FlightSearch.data.repository.sqlRepository.AirportRepository;
import com.FlightSearch.FlightSearch.model.AirportRequest;
import com.FlightSearch.FlightSearch.model.AirportResponse;
import com.FlightSearch.FlightSearch.service.AirportReader;
import com.FlightSearch.FlightSearch.service.AirportServices;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@IllegalExceptionProcessing
@RequestMapping("/airport")
public class AirportController {
    private final AirportServices airportServices;
    private final AirportReader airportReader;

    public AirportController(AirportServices airportServices, AirportReader airportReader, AirportRepository airportRepository) {
        this.airportServices = airportServices;
        this.airportReader = airportReader;
    }

    @GetMapping("searchByIataCode")
    public boolean checkAirportExistsFromIataCode(@RequestParam(required = true)@NotEmpty String iataCode) {
        return airportServices.findExistingAirportByIataCode(iataCode);
    }
    @GetMapping("searchByLocation")
    public AirportResponse searchAirportByLocation(@RequestParam(required = true) String location) {
        return airportServices.findAirportByDepartureFrom(location);
    }

    @GetMapping("searchById/{id}")
    public ResponseEntity<AirportResponse> searchAirportById(@PathVariable Integer id) {
//        if (!airportRepository.existsById(airportId)) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(airportServices.findAirportById(id));
    }

    @GetMapping("/addAirports")
    public String addAirports() {
        airportReader.saveAirportsDataFromList();
        return "Airports's been added";
    }
    @Transactional
    @PutMapping("/updateAirport/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable int id, @Valid @RequestBody AirportRequest source) {
//        if (!airportRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        } else {
//            airportServices.updaterAirport(id, source);
//        }
//            return ResponseEntity.noContent().build();
        if (airportServices.updateAirport(id, source) != null){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
