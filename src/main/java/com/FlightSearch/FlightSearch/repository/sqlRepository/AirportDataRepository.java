package com.FlightSearch.FlightSearch.repository.sqlRepository;

import com.FlightSearch.FlightSearch.repository.entities.AirportData;
import com.FlightSearch.FlightSearch.repository.entities.FlightData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AirportDataRepository extends JpaRepository<AirportData,Integer> {

    List<AirportData> findAll();

    Optional<AirportData> findById(Integer id);

    List<AirportData> findByLocation(String location);

    AirportData findByName(String name);

    AirportData save(AirportData entity);

    boolean existsByIataCode (String iataCode);

//    @Modifying
//    @Query(value = """
//            delete from AirportData a
//            set a.flightsData =
//            (select f from FlightData f
//            where f.id != :flightId and
//            f.airportData.id = :airportId)
//            where a.id = :airportId
//            """)
   // f.airportData.id = :airportId
    //void removeFlightDataFromAirportDataInFlightsList(Long flightId);
}
//    rom FlightData f
//        where f.departureTo = :returnDepartureTo
//        and f.arrivalTo = :returnArrivalTo
//        and f.numberOfSeatsAvailable >= :numberOfPassengers
//        and date(f.departureDate) = (
//        select date(f2.departureDate)
//        from FlightData f2
//        where f2.departureTo = :returnDepartureTo
//        and f2.arrivalTo = :returnArrivalTo
//        and f2.departureDate > :returnDepartureDate
//        and f2.numberOfSeatsAvailable >= :numberOfPassengers
//        order by f2.departureDate ASC
//        limit 1
//        )
//        """)