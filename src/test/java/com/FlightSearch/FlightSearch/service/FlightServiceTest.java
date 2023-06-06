package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.controller.model.CreateFlightRequest;
import com.FlightSearch.FlightSearch.controller.model.FlightResponse;
import com.FlightSearch.FlightSearch.controller.model.Trip;
import com.FlightSearch.FlightSearch.service.model.Airport;
import com.FlightSearch.FlightSearch.service.model.Flight;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    SqlRepository sqlRepository;

    @InjectMocks
    FlightService flightService;

    @Test
    void shouldMakeFlightFromCreateFlightRequest() {
        // given
        Airport airport = new Airport(101, "wroclaw", null, null, null, null, null);
        when(sqlRepository.findAirportById(anyInt())).thenReturn(Optional.of(airport));
        CreateFlightRequest flightRequest =
                new CreateFlightRequest(3L, "234", "WROCLAW", "LIVERPOOL",
                        LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                        BigDecimal.valueOf(30), 60, 101);

        // when
        Flight flight = flightService.makeFlightFromCreateFlightRequest(flightRequest);

        // then
        assertThat(flight).isNotNull();
        assertThat(flight.getAirport()).isNotNull();
        assertEquals(101, flight.getAirport().getId());
        assertEquals("wroclaw", flight.getAirport().getName());
        assertEquals(60, flight.getNumberOfSeatsAvailable());
    }

    //    public Flight makeFlightFromCreateFlightRequest(CreateFlightRequest flightRequest) {
//        Flight flight = new Flight(flightRequest);
//        sqlRepository.findById(flightRequest.getAirportId()).ifPresent(airport -> flight.setAirport(airport));
//        return flight;
//    }
    @Test
    void shouldMakeFlightResponseFromFlight() {
        //given
        Flight flight =
                new Flight(3L, "234", "WROCLAW", "LIVERPOOL",
                        LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                        BigDecimal.valueOf(30), 60, new Airport(101, "dsf", "koa",
                        "kola", "lala", (double) 14L, (double) 15L));
        when(sqlRepository.findFlightById(anyLong())).thenReturn(Optional.of(flight));
        Long flightId = flight.getId();
        //when
        FlightResponse flightResponse = flightService.makeFlightResponseFromFlight(flightId);
        //then
        assertThat(flightResponse).isNotNull();
        assertThat(flightResponse.getId()).isNotNull();
        assertEquals(3L, flightResponse.getId());
        assertEquals("WROCLAW", flightResponse.getDepartureTo());
        assertEquals(60, flightResponse.getNumberOfSeatsAvailable());
        assertNotEquals(101, flightResponse.getId());

    }

    //    public FlightResponse makeFlightResponseFromFlight(Long flightId) {
//        Flight flight = sqlRepository.findFlightById(flightId).get();
//        FlightResponse flightResponse = new FlightResponse(flight);
//        return flightResponse;
//    }
    @Test
    void shouldAddFlight() {
        //given
        CreateFlightRequest flightRequest =
                new CreateFlightRequest(3L, "234", "WROCLAW", "LIVERPOOL",
                        LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                        BigDecimal.valueOf(30), 60, 101);
        Flight flight =
                new Flight(3L, "234", "WROCLAW", "LIVERPOOL",
                        LocalDateTime.parse("2024-01-03T18:30:00"), LocalDateTime.parse("2024-01-03T19:30:00"),
                        BigDecimal.valueOf(30), 60, new Airport(101, "dsf", "koa",
                        "kola", "lala", (double) 14L, (double) 15L));
        when(sqlRepository.saveFlight(any(Flight.class))).thenReturn(flight);
        //when
        Long flightIdAddedFlight = flightService.addFlight(flightRequest);
        //then
        assertThat(flightIdAddedFlight).isNotNull();
        assertEquals(3L, flightIdAddedFlight);
        //assertEquals("WROCLAW", flightIdAddedFlig);
        //assertEquals(60, flightResponse.getNumberOfSeatsAvailable());
        //assertNotEquals(101,flightResponse.getId());
    }
//public Long addFlight(CreateFlightRequest flightRequest) {
//    Flight flight = makeFlightFromCreateFlightRequest(flightRequest);
//    Long flightId = sqlRepository.saveFlight(flight).getId();
//    return flightId;
//}

    @Test
    void shouldSearchMatchingFlightsInConditionToTrip() {
        //given
        Trip trip = new Trip("WROCLAW", "LIVERPOOL", LocalDateTime.parse("2023-12-01T12:00:00"),
                false, 2);
        List<Flight> mockMatchingdFlights = new ArrayList<>();
        mockMatchingdFlights.add(new Flight(10L, "234", "WROCLAW", "LIVERPOOL",
                LocalDateTime.parse("2023-12-01T13:45:00"), LocalDateTime.parse("2023-12-01T14:45:00"),
                BigDecimal.valueOf(30), 60, new Airport(101, "WROCLAW", "WROCLAW",
                "WRO", "POLAND", (double) 14L, (double) 15L)));
        mockMatchingdFlights.add(new Flight(11L, "236", "WROCLAW", "LIVERPOOL",
                LocalDateTime.parse("2023-12-02T13:45:00"), LocalDateTime.parse("2023-12-02T14:45:00"),
                BigDecimal.valueOf(30), 34, new Airport(101, "WROCLAW", "WROCLAW",
                "WRO", "POLAND", (double) 14L, (double) 15L)));
        when(sqlRepository.findMatch(trip.getDepartureTo(), trip.getArrivalTo(), trip.getDepartureDate(), trip.getNumberOfPassengers()))
                .thenReturn(mockMatchingdFlights);

        List<Flight> mockMatchingReturnFlights = new ArrayList<>();
//        mockMatchedReturnFlights.add(new Flight(10L, "114", "LIVERPOOL", "WROCLAW",
//                LocalDateTime.parse("2023-12-04T14:45:00"), LocalDateTime.parse("2023-12-04T17:45:00"),
//                BigDecimal.valueOf(30), 60, new Airport(102, "LIVERPOOL", "LIVERPOOL",
//                "LIV", "ENGLAND", (double) 14L, (double) 0L)));
//        mockMatchedReturnFlights.add(new Flight(11L, "216", "LIVERPOOL", "WROCLAW",
//                LocalDateTime.parse("2023-12-04T18:45:00"), LocalDateTime.parse("2023-12-04T21:45:00"),
//                BigDecimal.valueOf(30), 34, new Airport(102, "LIVERPOOL", "LIVERPOOL",
//                "LIV", "ENGLAND", (double) 14L, (double) 0L)));
//        when(sqlRepository.findReturnMatch(trip.getArrivalTo(),trip.getDepartureTo(),trip.getReturnDepartureDate(),trip.getNumberOfPassengers()))
//                .thenReturn(mockMatchedReturnFlights);
        when(sqlRepository.findReturnMatch(trip.getArrivalTo(), trip.getDepartureTo(), trip.getReturnDepartureDate(), trip.getNumberOfPassengers()))
                .thenReturn(mockMatchingReturnFlights);
        //when
        List<List<FlightResponse>> listsOfFlightsResponse = flightService.searchMatchingFlights(trip);
        //then
        assertThat(listsOfFlightsResponse).isNotNull();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            listsOfFlightsResponse.get(1);
        });
        assertEquals(1, listsOfFlightsResponse.size());
        assertEquals(mockMatchingdFlights.size(), listsOfFlightsResponse.get(0).size());
        assertEquals(mockMatchingdFlights.get(0).getId(), listsOfFlightsResponse.get(0).get(0).getId());

    }


//public List<List<FlightResponse>> searchMatchingFlights(Trip trip) {
//    List<List<Flight>> matchingFlights = new ArrayList<>();
//    List<Flight> flights = sqlRepository.findMatch(trip.getDepartureTo(), trip.getArrivalTo(), trip.getDepartureDate(), trip.getNumberOfPassengers());
//    List<Flight> returnFlights = sqlRepository.findReturnMatch(trip.getArrivalTo(), trip.getDepartureTo(), trip.getReturnDepartureDate(), trip.getNumberOfPassengers());
//    matchingFlights.add(flights);
//    if (returnFlights.size() != 0) {
//        matchingFlights.add(returnFlights);
//    }
//    return makeFlightsResponseFromFlights(matchingFlights);
//}
    @Test
    void shouldMakeFlightsResponseFromFlights(){
        //given
        List<List<Flight>> matchingFlights = new ArrayList<>(new ArrayList<>());
        List<Flight> matchingFlightsToDestination = new ArrayList<>();
        matchingFlightsToDestination.add(new Flight(10L, "234", "WROCLAW", "LIVERPOOL",
                LocalDateTime.parse("2023-12-01T13:45:00"), LocalDateTime.parse("2023-12-01T14:45:00"),
                BigDecimal.valueOf(30), 60, new Airport(101, "WROCLAW", "WROCLAW",
                "WRO", "POLAND", (double) 14L, (double) 15L)));
        matchingFlightsToDestination.add(new Flight(11L, "236", "WROCLAW", "LIVERPOOL",
                LocalDateTime.parse("2023-12-02T13:45:00"), LocalDateTime.parse("2023-12-02T14:45:00"),
                BigDecimal.valueOf(30), 34, new Airport(101, "WROCLAW", "WROCLAW",
                "WRO", "POLAND", (double) 14L, (double) 15L)));

        List<Flight> matchingReturnFlights = new ArrayList<>();
        matchingReturnFlights.add(new Flight(12L, "114", "LIVERPOOL", "WROCLAW",
                LocalDateTime.parse("2023-12-04T14:45:00"), LocalDateTime.parse("2023-12-04T17:45:00"),
                BigDecimal.valueOf(30), 60, new Airport(102, "LIVERPOOL", "LIVERPOOL",
                "LIV", "ENGLAND", (double) 14L, (double) 0L)));
        matchingReturnFlights.add(new Flight(15L, "216", "LIVERPOOL", "WROCLAW",
                LocalDateTime.parse("2023-12-04T18:45:00"), LocalDateTime.parse("2023-12-04T21:45:00"),
                BigDecimal.valueOf(30), 34, new Airport(102, "LIVERPOOL", "LIVERPOOL",
                "LIV", "ENGLAND", (double) 14L, (double) 0L)));
        matchingFlights.add(matchingFlightsToDestination);
        matchingFlights.add(matchingReturnFlights);

        //when
        List<List<FlightResponse>> listsOfMatchingFlightResponse = flightService.makeFlightsResponseFromFlights(matchingFlights);
        //then
        assertThat(listsOfMatchingFlightResponse).isNotNull();
        assertThat(listsOfMatchingFlightResponse.get(0)).isNotNull();
        assertThat(listsOfMatchingFlightResponse.get(1)).isNotNull();
        assertEquals(11L, listsOfMatchingFlightResponse.get(0).get(1).getId());
        assertEquals(12L, listsOfMatchingFlightResponse.get(1).get(0).getId());
        assertEquals(matchingFlights.size(), listsOfMatchingFlightResponse.size());
        assertNotEquals(listsOfMatchingFlightResponse.get(0), listsOfMatchingFlightResponse.get(1));

    }
//    public List<List<FlightResponse>> makeFlightsResponseFromFlights(List<List<Flight>> matchingFlights) {
//        List<List<FlightResponse>> matchingFlightsResponse = matchingFlights.stream()
//                .map(flightList -> flightList.stream()
//                        .map(flight -> new FlightResponse(flight))
//                        .collect(Collectors.toList()))
//                .collect(Collectors.toList());
//        return matchingFlightsResponse;
//    }
}
