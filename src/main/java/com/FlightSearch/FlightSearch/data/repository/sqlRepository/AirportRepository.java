package com.FlightSearch.FlightSearch.data.repository.sqlRepository;

import com.FlightSearch.FlightSearch.data.entities.AirportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AirportRepository extends JpaRepository<AirportData,Integer> {

    List<AirportData> findAll();

    Optional<AirportData> findById(Integer id);

    AirportData findByLocation(String location);

    AirportData save(AirportData entity);

    boolean existsByIataCode (String iataCode);
}
