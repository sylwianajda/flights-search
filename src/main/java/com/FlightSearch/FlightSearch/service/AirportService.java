package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.repository.entities.Airport;
import com.FlightSearch.FlightSearch.model.AirportRequest;
import com.FlightSearch.FlightSearch.model.AirportResponse;
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
        //AirportData airportData = airportDataRepository.findByLocation(location);
        //AirportResponse result = AirportResponse.from(Airport.from(airportData));
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
    @Transactional
    public AirportResponse executeAirportUpdate(int id, AirportRequest airportRequest) {
            Airport source = new Airport(airportRequest);
         sqlRepository.findAirportById(id).ifPresent(airport -> {
             airport.updateAirport(source);
//             source.setFlights(airport.getFlights());
//            airport.updateAirport(createAirportUpdate(source));
//            airport.setFlights(sqlRepository.findById(id).get().getFlights());
            sqlRepository.saveAirport(airport);
        });
            AirportResponse updatedAirport = new AirportResponse(sqlRepository.findAirportById(id).get());
            return updatedAirport;
//            if (airportRepository.findById(id).get().toString().equals(airportData.toString())){
//                return updatedAirport;
//            } else {
//                return null;
//            }
    }
    public Airport createAirportUpdate(Airport source) {
        return new Airport(
                source.getId(),
                source.getLocation(),
                source.getLocation(),
                source.getIataCode(),
                source.getCountry(),
                source.getLatitude(),
                source.getLongitude());
                //source.getFlights());

    }


}
