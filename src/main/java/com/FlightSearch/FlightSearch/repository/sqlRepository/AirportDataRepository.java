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

}
