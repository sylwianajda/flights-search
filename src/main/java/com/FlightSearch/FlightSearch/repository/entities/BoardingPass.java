package com.FlightSearch.FlightSearch.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoardingPass {
    private long boardingPassId;
    private String firstName;
    private String lastName;
    private FlightData flightData;

    public BoardingPass(BoardingPassData boardingPassData) {

    }
}
