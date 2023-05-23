package com.FlightSearch.FlightSearch.service;

import com.FlightSearch.FlightSearch.model.WeightFlightsPath;
import com.FlightSearch.FlightSearch.repository.entities.Flight;
import es.usc.citius.hipster.algorithm.Algorithm;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HashBasedHipsterDirectedGraph;
import es.usc.citius.hipster.model.impl.WeightedNode;
import es.usc.citius.hipster.model.problem.SearchProblem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
@Service
public class FlightChecker {

        public List<String> findShortestPath(String start, String end, List<Flight> flights) {

            // Create a simple weighted directed graph with Hipster where
            // vertices are Strings and edge values are just doubles
            HashBasedHipsterDirectedGraph<String, BigDecimal> graph = HashBasedHipsterDirectedGraph.create();
            for (Flight flight : flights) {
                graph.add(flight.getDepartureTo());
                graph.add(flight.getArrivalTo());
                graph.connect(flight.getDepartureTo(), flight.getArrivalTo(), flight.getPrice());
            }

            // Create the search problem. For graph problems, just use
            // the GraphSearchProblem util class to generate the problem with ease.
            SearchProblem<BigDecimal, String, WeightedNode<BigDecimal, String, Double>> p = GraphSearchProblem
                    .startingFrom(start)
                    .in(graph)
                    .takeCostsFromEdges()
                    .build();

            // Search the shortest path from "start" to "end"
            //System.out.println(Hipster.createDijkstra(p).search(end));
            Algorithm<BigDecimal, String, WeightedNode<BigDecimal, String, Double>>.SearchResult search = Hipster.createDijkstra(p).search(end);
            System.out.println(search);
            return null;
        }





    public List<String> findShortestPathUla(String start, String end, List<WeightFlightsPath> costOfFlights) {


        // Create a simple weighted directed graph with Hipster where
        // vertices are Strings and edge values are just doubles
        HashBasedHipsterDirectedGraph<String, BigDecimal> graph = HashBasedHipsterDirectedGraph.create();
        for (WeightFlightsPath weightFlightsPath : costOfFlights) {
            graph.add(start);
            graph.add(end);
            graph.connect(start, end, weightFlightsPath.getSumOfPrices());

        }


        // Create the search problem. For graph problems, just use
        // the GraphSearchProblem util class to generate the problem with ease.
        SearchProblem p = GraphSearchProblem
                .startingFrom(start)
                .in(graph)
                .takeCostsFromEdges()
                .build();

        // Search the shortest path from "start" to "end"
        //System.out.println(Hipster.createDijkstra(p).search(end));
        Algorithm.SearchResult search = Hipster.createDijkstra(p).search(end);



        System.out.println(search);
        return null;
    }
//
//
//            // Create a graph of flights
//            Map<String, List<Flight>> graph = new HashMap<>();
//            for (Flight flight : flights) {
//                if (!graph.containsKey(flight.getDepartureTo())) {
//                    graph.put(flight.getDepartureTo(), new ArrayList<>());
//                }
//                graph.get(flight.getDepartureTo()).add(flight);
//            }
//
//            // Initialize distance and previous node maps
//            Map<String, Double> distance = new HashMap<>();
//            Map<String, String> previous = new HashMap<>();
//            Set<String> visited = new HashSet<>(); ///????
//
//            for (String airport : graph.keySet()) {
//                for(int i = 0; i< graph.get(airport).size(); i++) {
//                    distance.put(airport, Double.MAX_VALUE);
//                    previous.put(airport, null);
//                }
//            }
//            //????
////            if(distance.containsKey(start)) {
////                distance.replace(start, 0.0);
////            }
//
//            // Initialize priority queue
//            PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(key -> distance.get(key)));
//            //queue.add(start);
//
//            // Dijkstra's algorithm
//            while (!queue.isEmpty()) {
//                String current = queue.remove();
//                visited.add(current);
//                if (current.equals(end)) {
//                    break;
//                }
//                if (!graph.containsKey(current)) {
//                    continue;
//                }
//                for (Flight flight : graph.get(current)) {
//                    Double newDistance = distance.get(current) + flight.getPrice().doubleValue();
//                    if (newDistance < distance.get(flight.getArrivalTo())) {
//                        distance.put(flight.getArrivalTo(), newDistance);
//                        previous.put(flight.getArrivalTo(), current);
//                        queue.remove(flight.getArrivalTo());
//                        queue.add(flight.getArrivalTo());
//                    }
//                }
//            }
//
//            // Backtrack to find shortest path
//            List<String> path = new ArrayList<>();
//            String current = end;
//            while (current != null) {
//                path.add(current);
//                current = previous.get(current);
//            }
//            Collections.reverse(path);
//            return path;
//        }


}
