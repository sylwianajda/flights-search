package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.service.model.Airport;
import com.FlightSearch.FlightSearch.repository.sqlRepository.SqlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class AirportReader {
    private SqlRepository sqlRepository;

    public AirportReader(SqlRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
    }

    public static List<Airport> readAirportFromFile(String filePath) {
        List<Airport> airports = new ArrayList<>();
        Path pathToFile = Paths.get(filePath);

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {

            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split(":");

                Airport airport = createAirport(attributes);

                airports.add(airport);

                line = br.readLine();

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        airports.removeIf(airport -> airport.getLatitude() == 0d && airport.getLongitude() == 0d);

        return airports;
    }

    private static Airport createAirport(String[] metadata) {
         String name = metadata[2];
         String location = metadata[3];
         String iataCode = metadata[1];
         String country = metadata[4];
         Double latitude = Double.parseDouble(metadata[14]);
         Double longitude = Double.parseDouble(metadata[15]);

        return new Airport(name,location,iataCode,country,latitude,longitude);
    }
    @Transactional
    public boolean saveAirportsDataFromList() {
        List<Airport> airports = readAirportFromFile("/home/sylvia/Downloads/GlobalAirportDatabase.txt");
        try {
            for (Airport a : airports) {
                sqlRepository.saveAirport(a);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

