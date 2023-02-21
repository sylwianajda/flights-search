package com.FlightSearch.FlightSearch.repository.sqlRepository;

import com.FlightSearch.FlightSearch.model.BoardingPass;
import com.FlightSearch.FlightSearch.model.Flight;
import com.FlightSearch.FlightSearch.repository.ApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class SqlRepository implements ApiRepository {
    private AirportRepository airportRepository;
    private FlightRepository flightRepository;
    private BoardingPassRepository boardingPassRepository;


    public SqlRepository(AirportRepository airportRepository, FlightRepository flightRepository, BoardingPassRepository boardingPassRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
        this.boardingPassRepository = boardingPassRepository;
    }


    @Override
    public List<Flight> findMatch() {
        return Collections.emptyList();
    }

    @Override
    public BoardingPass save() {
        return null;
    }
}
