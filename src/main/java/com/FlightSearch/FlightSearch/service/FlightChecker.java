package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.repository.entities.Flight;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Parser;
import java.util.*;
@Service
public class FlightChecker {

        public List<String> findShortestPath(String start, String end, List<Flight> flights) {
            // Create a graph of flights
            Map<String, List<Flight>> graph = new HashMap<>();
            for (Flight flight : flights) {
                if (!graph.containsKey(flight.getDepartureTo())) {
                    graph.put(flight.getDepartureTo(), new ArrayList<>());
                }
                graph.get(flight.getDepartureTo()).add(flight);
            }

            // Initialize distance and previous node maps
            Map<String, Double> distance = new HashMap<>();
            Map<String, String> previous = new HashMap<>();
            Set<String> visited = new HashSet<>();

            for (String airport : graph.keySet()) {
                distance.put(airport, Double.MAX_VALUE);
                //previous.put(airport, null);
            }
            //????
            distance.put(start, 0.0);

            // Initialize priority queue
            PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(key -> distance.get(key)));
            queue.add(start);

            // Dijkstra's algorithm
            while (!queue.isEmpty()) {
                String current = queue.poll();//.remove();
                visited.add(current);
                if (current.equals(end)) {
                    break;
                }
                if (!graph.containsKey(current)) {
                    continue;
                }
                for (Flight flight : graph.get(current)) {
                    Double newDistance = distance.get(current) + flight.getPrice().doubleValue();
                    if (newDistance < distance.get(flight.getArrivalTo())) {
                        distance.put(flight.getArrivalTo(), newDistance);
                        previous.put(flight.getArrivalTo(), current);
                        queue.remove(flight.getArrivalTo());
                        queue.add(flight.getArrivalTo());
                    }
                }
            }

            // Backtrack to find shortest path
            List<String> path = new ArrayList<>();
            String current = end;
            while (current != null) {
                path.add(current);
                current = previous.get(current);
            }
            Collections.reverse(path);
            return path;
        }
    }
