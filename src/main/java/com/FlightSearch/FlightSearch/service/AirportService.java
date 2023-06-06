package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.service.model.Airport;
import com.FlightSearch.FlightSearch.controller.model.AirportRequest;
import com.FlightSearch.FlightSearch.controller.model.AirportResponse;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private SqlRepository sqlRepository;
    public AirportService(SqlRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
    }
    public List<AirportResponse> findAirportByDepartureFrom(String location) {
        List<Airport> airports= sqlRepository.findByLocation(location);
        List<AirportResponse> result = airports.stream()
                .map(airport -> new AirportResponse(airport))
                .collect(Collectors.toList());
        return result;
    }
    public boolean findExistingAirportByIataCode(String iataCode) {
        boolean airportExist = sqlRepository.existsByIataCode(iataCode);
        return airportExist;
    }
    public AirportResponse findAirportById(Integer airportId) {
        Airport airport = sqlRepository.findAirportById(airportId).get();
        AirportResponse result = new AirportResponse(airport);
        return result;
    }
    public Airport searchFlightsByName(String name) {
        return sqlRepository.findAirportByName(name);
    }
    @Transactional
    public AirportResponse executeAirportUpdate(int id, AirportRequest airportRequest) {
            Airport source = new Airport(airportRequest);
         sqlRepository.findAirportById(id).ifPresent(airport -> {
             airport.updateAirport(source);

            sqlRepository.saveAirport(airport);
        });
            AirportResponse updatedAirport = new AirportResponse(sqlRepository.findAirportById(id).get());
            return updatedAirport;
    }

}
