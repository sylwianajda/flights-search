package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.Airport;
import com.FlightSearch.FlightSearch.repository.sqlRepository.AirportRepository;
import org.springframework.stereotype.Service;

@Service
public class AirportServices {
    private final AirportRepository airportRepository;

    public AirportServices(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;

    }
    public Airport airportFinderByDepartureFrom(String departureFrom) {
        Airport airport = airportRepository.findByLocation(departureFrom);
        return airport;
    }
}
