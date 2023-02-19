package com.FlightSearch.FlightSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="boarding_passes")
public class BoardingPass {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private long boardingPassId;
        private String firstName;
        private String lastName;
//        @Embedded
//        private FlightDto flightDto = new FlightDto(flightflight.getId());
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "flight_id")
        private Flight flight;

        public BoardingPass(String firstName, String lastName, Flight flight) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.flight = flight;
        }

        public Flight getFlight() {
                return flight;
        }

        public void setFlight(Flight flight) {
                this.flight = flight;
        }
}

//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @Column(name = "id", nullable = false)
//        private long boardingPassId;
//        private long flightNumber;
//        //private int seatNumber;
//        private String firstName;
//        private String lastName;
//        private LocalDateTime departDate;
//        private Time gateClosedTime;
//        //private LocalDateTime bookingTime;
//        private FlightDto flight;
//
//}
