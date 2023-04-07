package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.repository.entities.Airport;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AirportReaderTest {
    @Mock
    SqlRepository sqlRepository;

    @InjectMocks
    AirportReader airportReader;
    @Test
    void shouldReadAndSaveAirportsFromList() {
        // given
        // when
        airportReader.saveAirportsDataFromList();
        // then
//        public static List<Airport> readAirportFromFile(String filePath) {
//            List<Airport> airports = new ArrayList<>();
//            Path pathToFile = Paths.get(filePath);
//
//            try (BufferedReader br = Files.newBufferedReader(pathToFile,
//                    StandardCharsets.UTF_8)) {
//
//                String line = br.readLine();
//
//                while (line != null) {
//
//                    String[] attributes = line.split(":");
//
//                    Airport airport = createAirport(attributes);
//
//                    airports.add(airport);
//
//                    line = br.readLine();
//
//                }
//
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//            airports.removeIf(airport -> airport.getLatitude() == 0d && airport.getLongitude() == 0d);
//            return airports;
//        }
    }
}
