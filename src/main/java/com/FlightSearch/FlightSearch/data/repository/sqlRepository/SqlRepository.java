//package com.FlightSearch.FlightSearch.data.repository.sqlRepository;
//
//import com.FlightSearch.FlightSearch.data.entities.AirportData;
//import com.FlightSearch.FlightSearch.data.entities.BoardingPassData;
//import com.FlightSearch.FlightSearch.data.entities.FlightData;
//import com.FlightSearch.FlightSearch.data.repository.ApiRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class SqlRepository implements ApiRepository {
//    private AirportRepository airportRepository;
//    private FlightRepository flightRepository;
//    private BoardingPassRepository boardingPassRepository;
//
//    public SqlRepository(AirportRepository airportRepository, FlightRepository flightRepository, BoardingPassRepository boardingPassRepository) {
//        this.airportRepository = airportRepository;
//        this.flightRepository = flightRepository;
//        this.boardingPassRepository = boardingPassRepository;
//    }
//
//    @Override
//    public List<FlightData> findAll() {
//        return null;
//    }
//
//    @Override
//    public Optional<AirportData> findById(Integer id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public AirportData findByLocation() {
//        return null;
//    }
//
//    @Override
//    public boolean existsByIataCode() {
//        return false;
//    }
//
//    @Override
//    public BoardingPassData save() {
//        return null;
//    }
//
//    @Override
//    public Optional<FlightData> findByDepartureTo() {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<FlightData> findById() {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<FlightData> findAllByAirportId(Integer airportId) {
//        return null;
//    }
//
//    @Override
//    public List<FlightData> findMatch() {
//        return null;
//    }
//
//    @Override
//    public List<FlightData> findReturnMatch() {
//        return null;
//    }
//}
