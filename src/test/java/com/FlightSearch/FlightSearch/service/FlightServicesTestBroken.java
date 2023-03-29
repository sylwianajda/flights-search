package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.repository.entities.Airport;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightServicesTestBroken {


    @Test
    void shouldAddFlight(){

    }

    @Test
    void shouldMakeFlightFromCreateFlightRequest(){
        //given
        SqlRepository mockSqlRepository = mock(SqlRepository.class);
        Optional<Flight> flight1 = Optional.of(new Flight(3L, "234", "WROCLAW", "LIVERPOOL",
                LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                BigDecimal.valueOf(30), 60, new Airport(101, "dsf", "koa",
                "kola", "lala", (double) 14L, (double) 15L)));
        when(mockSqlRepository.findFlightById(anyLong())).thenReturn(flight1);
        CreateFlightRequest flightRequest =
                new CreateFlightRequest(3L, "234", "WROCLAW", "LIVERPOOL",
                        LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                        BigDecimal.valueOf(30), 60, 101);
        //when
        Flight flight = new Flight(flightRequest);
        mockSqlRepository.findAirportById(flightRequest.getAirportId()).ifPresent(airport -> flight.setAirport(airport));
        //then
        assertEquals(101,flightRequest.getAirportId());

    }
//    @Test
//    void shouldNotMakeFlightFromCreateFlightRequest(){
//        //given
//        SqlRepository mockSqlRepository = mock(SqlRepository.class);
//        when(mockSqlRepository.findById(anyLong())).thenReturn(Optional.empty());
//        CreateFlightRequest flightRequest = new CreateFlightRequest();
//        //when
//        Flight flight = new Flight(flightRequest);
//        mockSqlRepository.findById(flightRequest.getAirportId()).ifPresent(airport -> flight.setAirport(airport));
//        //then
//        assertThat(flight);
//
//    }
}
