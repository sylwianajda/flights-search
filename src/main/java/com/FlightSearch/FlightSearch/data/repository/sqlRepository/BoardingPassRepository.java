package com.FlightSearch.FlightSearch.data.repository.sqlRepository;

import com.FlightSearch.FlightSearch.data.entities.BoardingPassData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardingPassRepository extends JpaRepository<BoardingPassData,Long> {
    BoardingPassData save(BoardingPassData entity);

}
