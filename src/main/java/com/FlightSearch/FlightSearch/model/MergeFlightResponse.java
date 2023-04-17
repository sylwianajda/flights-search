package com.FlightSearch.FlightSearch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class MergeFlightResponse {
    List<List<FlightResponse>> flights;

    List<List<FlightResponse>> returnFlights;
}
