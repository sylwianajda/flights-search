package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.repository.sqlRepository.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class FlightServices {
    private final FlightRepository flightRepository;

    public FlightServices(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

//    public FlightDto createFlightDto(Flight flight){
//        var result = new FlightDto();
//        result.setDescription(description);
//        result.setTasks(tasks.stream().map(source ->source.toTask(result))
//                .collect(Collectors.toSet()));
//        result.setProject(project);
//        return result;
//    }
//    public FlightDto createFlightDto(Flight flight) {
//        FlightDto result = taskGroupRepository.save(source.toGroup(project));
//        return new FlightDto(result);
//    }


}
