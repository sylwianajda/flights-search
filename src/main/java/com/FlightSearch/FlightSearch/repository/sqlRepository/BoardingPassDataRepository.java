package com.FlightSearch.FlightSearch.repository.sqlRepository;

import com.FlightSearch.FlightSearch.repository.entities.BoardingPassData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardingPassDataRepository extends JpaRepository<BoardingPassData,Long> {
    BoardingPassData save(BoardingPassData entity);

}
