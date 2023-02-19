package com.FlightSearch.FlightSearch.repository;

import com.FlightSearch.FlightSearch.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findAll();

    Optional<Flight> findByDepartureTo(String departureTo);

    Optional<Flight> findById(Long id);

    Flight save(Flight entity);

    List<Flight> findAllByAirportId(Integer airportId);

    @Query(value = """
            from Flight f
            where f.departureTo = :departureTo and
            f.arrivalTo = :arrivalTo and
            f.numberOfSeatsAvailable >= :numberOfPassengers and
             date(f.departureDate) = (
                select date(f2.departureDate)
                from Flight f2
                where f2.departureTo = :departureTo
                and f2.arrivalTo = :arrivalTo
                and f2.departureDate > :departureDate
                and f2.numberOfSeatsAvailable >= :numberOfPassengers
                order by f2.departureDate ASC
                limit 1
            )
            """)
    List<Flight> findMatch(@Param("departureTo") String departureTo, @Param("arrivalTo") String arrivalTo, @Param("departureDate") LocalDateTime departureDate, @Param("numberOfPassengers") int numberOfPassengers);

    @Query(value = """
            from Flight f
            where f.departureTo = :returnDepartureTo
            and f.arrivalTo = :returnArrivalTo
            and f.numberOfSeatsAvailable >= :numberOfPassengers 
            and date(f.departureDate) = (
                select date(f2.departureDate)
                from Flight f2
                where f2.departureTo = :returnDepartureTo
                and f2.arrivalTo = :returnArrivalTo
                and f2.departureDate > :returnDepartureDate
                and f2.numberOfSeatsAvailable >= :numberOfPassengers
                order by f2.departureDate ASC
                limit 1
            )
            """)
    List<Flight> findReturnMatch(@Param("returnDepartureTo") String returnDepartureTo, @Param("returnArrivalTo") String returnArrivalTo, @Param("returnDepartureDate") LocalDateTime returnDepartureDate, int numberOfPassengers);

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