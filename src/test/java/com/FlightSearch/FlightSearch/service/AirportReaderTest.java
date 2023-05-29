package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
