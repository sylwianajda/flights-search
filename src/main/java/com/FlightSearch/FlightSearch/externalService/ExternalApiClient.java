package com.FlightSearch.FlightSearch.externalService;

import com.FlightSearch.FlightSearch.service.model.Trip;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ExternalApiClient {
    private final RestTemplate restTemplate;

    public ExternalApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<String> connectToExternalApi(Trip trip) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", "109353c6-6432-4acf-8e77-ef842f64a664");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Trip> entity = new HttpEntity<>(trip, headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8084/getFlight", HttpMethod.POST, entity, String.class);
        return exchange;
    }
}
