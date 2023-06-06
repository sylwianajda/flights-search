package com.FlightSearch.FlightSearch.utlis;

import com.FlightSearch.FlightSearch.service.FlightService;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

public class FlightDeleter {
    private FlightService flightService;
    @Scheduled(fixedDelay = 1440,initialDelay = 1,timeUnit = TimeUnit.MINUTES )
    void deleteFlightOlderThanWeek() {
        flightService.deleteOldFlights();
    }
}
