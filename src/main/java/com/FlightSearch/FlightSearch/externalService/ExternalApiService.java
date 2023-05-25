package com.FlightSearch.FlightSearch.externalService;

import com.FlightSearch.FlightSearch.controller.model.Trip;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ExternalApiService {
    public ResponseEntity<String> connectToExternalApi(Trip trip) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", "109353c6-6432-4acf-8e77-ef842f64a664");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Trip> entity = new HttpEntity<>(trip, headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8084/getFlight", HttpMethod.POST, entity, String.class);
        return exchange;
    }
}
