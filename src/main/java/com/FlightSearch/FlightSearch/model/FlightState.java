package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.repository.entities.FlightData;
import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightDataRepository;

public class FlightState {
    private final FlightDataRepository flightDataRepository;
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

    public FlightState(FlightDataRepository flightDataRepository, FlightData flightData) {
        this.flightDataRepository = flightDataRepository;
        this.flightData = flightData;
        this.currentNumberOfSeatsAvailable = flightData.getNumberOfSeatsAvailable();
    }
    public void nextOccupiedSeat() {
        currentNumberOfSeatsAvailable--;
        flightDataRepository.findById(getFlight().getId()).
                ifPresent(flight1 -> {flight1.setNumberOfSeatsAvailable(currentNumberOfSeatsAvailable);
                flightDataRepository.save(flight1);
                });
    }
    public void nextAvailableSeat() {
        currentNumberOfSeatsAvailable++;
        flightDataRepository.findById(getFlight().getId()).
                ifPresent(flight1 -> {flight1.setNumberOfSeatsAvailable(currentNumberOfSeatsAvailable);
                    flightDataRepository.save(flight1);
                });
    }

    public boolean availableSeatsIsFinished() {
        if (currentNumberOfSeatsAvailable == 0) {
            return true;
        }
        return false;
    }
}
