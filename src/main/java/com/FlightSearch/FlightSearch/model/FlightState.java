package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.data.entities.FlightData;
import com.FlightSearch.FlightSearch.data.repository.sqlRepository.FlightRepository;

public class FlightState {
    private final FlightRepository flightRepository;
    private FlightData flightData;
    private int currentNumberOfSeatsAvailable;

    public FlightData getFlight() {
        return flightData;
    }

    public void setFlight(FlightData flightData) {
        this.flightData = flightData;
    }

    public int getCurrentNumberOfSeatsAvailable() {
        return currentNumberOfSeatsAvailable;
    }

    public void setCurrentNumberOfSeatsAvailable(int currentNumberOfSeatsAvailable) {
        this.currentNumberOfSeatsAvailable = currentNumberOfSeatsAvailable;
    }

    public FlightState(FlightRepository flightRepository, FlightData flightData) {
        this.flightRepository = flightRepository;
        this.flightData = flightData;
        this.currentNumberOfSeatsAvailable = flightData.getNumberOfSeatsAvailable();
    }
    public void nextOccupiedSeat() {
        currentNumberOfSeatsAvailable--;
        flightRepository.findById(getFlight().getId()).
                ifPresent(flight1 -> {flight1.setNumberOfSeatsAvailable(currentNumberOfSeatsAvailable);
                flightRepository.save(flight1);
                });
    }
    public void nextAvailableSeat() {
        currentNumberOfSeatsAvailable++;
        flightRepository.findById(getFlight().getId()).
                ifPresent(flight1 -> {flight1.setNumberOfSeatsAvailable(currentNumberOfSeatsAvailable);
                    flightRepository.save(flight1);
                });
    }

    public boolean availableSeatsIsFinished() {
        if (currentNumberOfSeatsAvailable == 0) {
            return true;
        }
        return false;
    }
}
