package com.FlightSearch.FlightSearch.repository;

import com.FlightSearch.FlightSearch.model.BoardingPass;
import com.FlightSearch.FlightSearch.model.Flight;

import java.util.List;

public interface ApiRepository {

    List<Flight> findMatch();

    BoardingPass save();
}
