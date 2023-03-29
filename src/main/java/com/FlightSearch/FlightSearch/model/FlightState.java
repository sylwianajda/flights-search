package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;

public class FlightState {

    private SqlRepository sqlRepository;
    private Flight flight;
    private int currentNumberOfSeatsAvailable;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getCurrentNumberOfSeatsAvailable() {
        return currentNumberOfSeatsAvailable;
    }

    public void setCurrentNumberOfSeatsAvailable(int currentNumberOfSeatsAvailable) {
        this.currentNumberOfSeatsAvailable = currentNumberOfSeatsAvailable;
    }

    public FlightState(SqlRepository sqlRepository, Flight flight) {
        this.sqlRepository = sqlRepository;
        this.flight = flight;
        this.currentNumberOfSeatsAvailable = flight.getNumberOfSeatsAvailable();
    }
    public void nextOccupiedSeat() {
        currentNumberOfSeatsAvailable--;
        sqlRepository.findFlightById(getFlight().getId()).
                ifPresent(flight1 -> {flight1.setNumberOfSeatsAvailable(currentNumberOfSeatsAvailable);
                sqlRepository.saveFlight(flight1);
                });
    }
    public void nextAvailableSeat() {
        currentNumberOfSeatsAvailable++;
        sqlRepository.findFlightById(getFlight().getId()).
                ifPresent(flight1 -> {flight1.setNumberOfSeatsAvailable(currentNumberOfSeatsAvailable);
                    sqlRepository.saveFlight(flight1);
                });
    }

    public boolean availableSeatsIsFinished() {
        if (currentNumberOfSeatsAvailable == 0) {
            return true;
        }
        return false;
    }
}
