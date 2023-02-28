package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.data.entities.AirportData;
import com.FlightSearch.FlightSearch.data.repository.sqlRepository.AirportRepository;
import com.FlightSearch.FlightSearch.model.Airport;
import com.FlightSearch.FlightSearch.model.AirportRequest;
import com.FlightSearch.FlightSearch.model.AirportResponse;
import org.springframework.stereotype.Service;

@Service
public class AirportServices {
    private final AirportRepository airportRepository;


    public AirportServices(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;

    }
    public AirportResponse findAirportByDepartureFrom(String location) {
        AirportData airportData = airportRepository.findByLocation(location);
        AirportResponse result = AirportResponse.from(Airport.from(airportData));
        return result;
    }
    public boolean findExistingAirportByIataCode(String iataCode) {
        boolean airportExist = airportRepository.existsByIataCode(iataCode);
        return airportExist;
    }
    public AirportResponse findAirportById(Integer airportId) {
        AirportData airportData = airportRepository.findById(airportId).get();
        AirportResponse result = AirportResponse.from(Airport.from(airportData));
        return result;
    }
    public AirportResponse updateAirport(int id, AirportRequest source) {
            AirportData airportData = AirportData.from(Airport.from(source));
            airportRepository.findById(id).ifPresent(airport-> {
                airport.updateAirport(airportData);
                airportRepository.save(airport);
            });
            AirportResponse updatedAirport = AirportResponse.from(Airport.from(airportRepository.findById(id).get()));
            return updatedAirport;
//            if (airportRepository.findById(id).get().toString().equals(airportData.toString())){
//                return updatedAirport;
//            } else {
//                return null;
//            }
    }


}
