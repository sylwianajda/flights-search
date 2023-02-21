package com.FlightSearch.FlightSearch.model;

import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightRepository;

public class FlightState {
    private final FlightRepository flightRepository;
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

    public FlightState(FlightRepository flightRepository, Flight flight) {
        this.flightRepository = flightRepository;
        this.flight = flight;
        this.currentNumberOfSeatsAvailable = flight.getNumberOfSeatsAvailable();
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
