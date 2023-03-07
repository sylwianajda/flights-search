package com.FlightSearch.FlightSearch.repository.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="boarding_passes")
public class BoardingPassData {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private long boardingPassId;
        private String firstName;
        private String lastName;
//        @Embedded
//        private FlightDto flightDto = new FlightDto(flightflight.getId());
        @JsonIgnore
        @ManyToOne(fetch = FetchType.EAGER )
        @JoinColumn(name = "flight_id")
        private FlightData flightData;

        public BoardingPassData(String firstName, String lastName, FlightData flightData) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.flightData = flightData;
        }

        public BoardingPassData(BoardingPass boardingPass) {
        }

        public FlightData getFlightData() {
                return flightData;
        }

        public void setFlightData(FlightData flightData) {
                this.flightData = flightData;
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
