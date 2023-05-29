package com.FlightSearch.FlightSearch.repository.sqlRepository;

import com.FlightSearch.FlightSearch.repository.entities.BoardingPassData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardingPassDataRepository extends JpaRepository<BoardingPassData,Long> {
    BoardingPassData save(BoardingPassData entity);

    Optional<BoardingPassData> findById(Long id);

//    void deleteById(Long id);
    void delete(BoardingPassData boardingPassData);




}
