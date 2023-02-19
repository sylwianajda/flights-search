package com.FlightSearch.FlightSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @NotBlank
    private String flightNumber;
    @NotBlank
    private String departureTo;
    @NotBlank
    private String arrivalTo;
    @NotBlank
    private LocalDateTime departureDate;
    @NotBlank
    private LocalDateTime arrivalDate;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private int numberOfSeatsAvailable;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_id")
    private Airport airport;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "flight")
    private List<BoardingPass> boardingPasses;

    public Flight(long id, String flightNumber, String departureTo, String arrivalTo,
                  LocalDateTime departureDate, LocalDateTime arrivalDate, BigDecimal price,
                  int numberOfSeatsAvailable, Airport airport) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureTo = departureTo;
        this.arrivalTo = arrivalTo;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
        this.numberOfSeatsAvailable = numberOfSeatsAvailable;
        this.airport = airport;
    }
}
