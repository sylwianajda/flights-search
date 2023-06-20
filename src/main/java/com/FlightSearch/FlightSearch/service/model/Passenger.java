package com.FlightSearch.FlightSearch.service.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;


@Getter
@Setter
public class Passenger {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
