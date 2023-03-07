package com.FlightSearch.FlightSearch.repository.sqlRepository;

import com.FlightSearch.FlightSearch.repository.entities.AirportData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportDataRepository extends JpaRepository<AirportData,Integer> {

    List<AirportData> findAll();

    Optional<AirportData> findById(Integer id);

    List<AirportData> findByLocation(String location);

    AirportData save(AirportData entity);

    boolean existsByIataCode (String iataCode);
}
