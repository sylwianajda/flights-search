package com.FlightSearch.FlightSearch.repository.sqlRepository;

import com.FlightSearch.FlightSearch.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Integer> {

    List<Airport> findAll();
    Optional<Airport> findById(Integer id);

//    Optional<Airport> findByLocation(String location);
    Airport findByLocation(String location);

    Airport save(Airport entity);

    boolean existsByIataCode (String iataCode);
}
