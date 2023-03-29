package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.repository.entities.AirportData;
import com.FlightSearch.FlightSearch.repository.sqlRepository.AirportDataRepository;
import org.springframework.stereotype.Service;

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
    private AirportDataRepository airportDataRepository;

    public AirportReader(AirportDataRepository airportDataRepository) {
        this.airportDataRepository = airportDataRepository;
    }

    public static List<AirportData> readAirportFromFile(String filePath) {
        List<AirportData> airportsData = new ArrayList<>();
        Path pathToFile = Paths.get(filePath);

        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {

            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split(":");

                AirportData airportData = createAirport(attributes);

                airportsData.add(airportData);

                line = br.readLine();

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return airportsData;
    }

    private static AirportData createAirport(String[] metadata) {
         String name = metadata[2];
         String location = metadata[3];
         String iataCode = metadata[1];
         String country = metadata[4];
         Double latitude = Double.parseDouble(metadata[14]);
         Double longitude = Double.parseDouble(metadata[15]);

        return new AirportData(name,location,iataCode,country,latitude,longitude);
    }
    public void saveAirportsDataFromList() {
        List<AirportData> airportData = readAirportFromFile("/home/sylvia/Downloads/GlobalAirportDatabase.txt");
        for (AirportData a : airportData) {
            airportDataRepository.save(a);
        }
    }
}
