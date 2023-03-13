package com.FlightSearch.FlightSearch.repository.sqlRepository;

import com.FlightSearch.FlightSearch.repository.entities.FlightData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface FlightDataRepository extends JpaRepository<FlightData,Long> {
    List<FlightData> findAll();

    boolean existsById(Long id);

    Optional<FlightData> findByDepartureTo(String departureTo);

    Optional<FlightData> findById(Long id);

    FlightData save(FlightData entity);

    //List<FlightData> findAllByAirportId(Integer id);

    @Query(value = """
            from FlightData f
            where f.departureTo = :departureTo and
            f.arrivalTo = :arrivalTo and
            f.numberOfSeatsAvailable >= :numberOfPassengers and
             date(f.departureDate) = (
                select date(f2.departureDate)
                from FlightData f2
                where f2.departureTo = :departureTo
                and f2.arrivalTo = :arrivalTo
                and f2.departureDate > :departureDate
                and f2.numberOfSeatsAvailable >= :numberOfPassengers
                order by f2.departureDate ASC
                limit 1
            )
            """)
    List<FlightData> findMatch(@Param("departureTo") String departureTo, @Param("arrivalTo") String arrivalTo, @Param("departureDate") LocalDateTime departureDate, @Param("numberOfPassengers") int numberOfPassengers);

    @Query(value = """
            from FlightData f
            where f.departureTo = :returnDepartureTo
            and f.arrivalTo = :returnArrivalTo
            and f.numberOfSeatsAvailable >= :numberOfPassengers 
            and date(f.departureDate) = (
                select date(f2.departureDate)
                from FlightData f2
                where f2.departureTo = :returnDepartureTo
                and f2.arrivalTo = :returnArrivalTo
                and f2.departureDate > :returnDepartureDate
                and f2.numberOfSeatsAvailable >= :numberOfPassengers
                order by f2.departureDate ASC
                limit 1
            )
            """)
    List<FlightData> findReturnMatch(@Param("returnDepartureTo") String returnDepartureTo, @Param("returnArrivalTo") String returnArrivalTo, @Param("returnDepartureDate") LocalDateTime returnDepartureDate, int numberOfPassengers);

    @Query(value = """
            update FlightData f
            set f.numberOfSeatsAvailable=(f.numberOfSeatsAvailable + 1)
            where f.id =:id
            """)
    void increaseSeatsAvailable(@Param("id") Long flightId);

    //    @Query(value = "from Flight f where f.departureTo=:departureTo and f.arrivalTo=:arrivalTo and f.departureDate>=:departureDate order by f.departureDate ASC ")
//    List<Flight> findMatchInOneDay(@Param("departureTo") String departureTo, @Param("arrivalTo") String arrivalTo, @Param("departureDate") LocalDateTime departureDate);
//
//    @Query("select distinct g from TaskGroup g join fetch g.tasks")
//    List<Flight> findAllByA();

//    @Query(value = """
//            from Flight f
//            where f.departureTo = :departureTo and
//            f.arrivalTo = :arrivalTo and
//            date(f.departureDate) = (
//                select date(f2.departureDate)
//                from Flight f2
//                where f2.departureTo = :departureTo
//                and f2.arrivalTo = :arrivalTo
//                and f2.departureDate > :departureDate
//                order by f2.departureDate ASC
//                limit 1
//            )
//            """)
//    List<Flight> findMatchWithStop(@Param("departureTo") String departureTo, @Param("arrivalTo") String arrivalTo, @Param("departureDate") LocalDateTime departureDate);
}



/*

from Flight f
where f.departureTo = :departureTo and
f.arrivalTo = :arrivalTo and
f.departureDate = (select date(f2.departureDate) from Flight f2 where f2.departureDate > current_time() order by f2.departureDate ASC limit 1)

 */