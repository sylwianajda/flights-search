package com.FlightSearch.FlightSearch.externalService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ExternalConnectionConfiguration {
    @Bean
    RestTemplate externalConnectionRestTemplate() {

        return new RestTemplate();
    }
}
