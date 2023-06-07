//package com.FlightSearch.FlightSearch.service;
//
//import com.FlightSearch.FlightSearch.service.model.WeightFlightsPath;
//import es.usc.citius.hipster.algorithm.Algorithm;
//import es.usc.citius.hipster.algorithm.Hipster;
//import es.usc.citius.hipster.graph.GraphSearchProblem;
//import es.usc.citius.hipster.graph.HashBasedHipsterDirectedGraph;
//import es.usc.citius.hipster.model.impl.WeightedNode;
//import es.usc.citius.hipster.model.problem.SearchProblem;
//import org.springframework.stereotype.Service;
//import java.math.BigDecimal;
//import com.FlightSearch.FlightSearch.service.model.Flight;
//import java.util.*;
//
//@Service
//public class FlightChecker {
//
//        public List<String> findShortestPath(String start, String end, List<Flight> flights) {
//
//            // Create a simple weighted directed graph with Hipster where
//            // vertices are Strings and edge values are just doubles
//            HashBasedHipsterDirectedGraph<String, BigDecimal> graph = HashBasedHipsterDirectedGraph.create();
//            for (Flight flight : flights) {
//                graph.add(flight.getDepartureTo());
//                graph.add(flight.getArrivalTo());
//                graph.connect(flight.getDepartureTo(), flight.getArrivalTo(), flight.getPrice());
//            }
//
//            // Create the search problem. For graph problems, just use
//            // the GraphSearchProblem util class to generate the problem with ease.
//            SearchProblem<BigDecimal, String, WeightedNode<BigDecimal, String, Double>> p = GraphSearchProblem
//                    .startingFrom(start)
//                    .in(graph)
//                    .takeCostsFromEdges()
//                    .build();
//
//            // Search the shortest path from "start" to "end"
//            //System.out.println(Hipster.createDijkstra(p).search(end));
//            Algorithm<BigDecimal, String, WeightedNode<BigDecimal, String, Double>>.SearchResult search = Hipster.createDijkstra(p).search(end);
//            System.out.println(search);
//            return null;
//        }
//
//
//
//
//
//    public List<String> findShortestPathUla(String start, String end, List<WeightFlightsPath> costOfFlights) {
//
//
//        // Create a simple weighted directed graph with Hipster where
//        // vertices are Strings and edge values are just doubles
//        HashBasedHipsterDirectedGraph<String, BigDecimal> graph = HashBasedHipsterDirectedGraph.create();
//        for (WeightFlightsPath weightFlightsPath : costOfFlights) {
//            graph.add(start);
//            graph.add(end);
//            graph.connect(start, end, weightFlightsPath.getSumOfPrices());
//
//        }
//
//
//        // Create the search problem. For graph problems, just use
//        // the GraphSearchProblem util class to generate the problem with ease.
//        SearchProblem p = GraphSearchProblem
//                .startingFrom(start)
//                .in(graph)
//                .takeCostsFromEdges()
//                .build();
//
//        // Search the shortest path from "start" to "end"
//        //System.out.println(Hipster.createDijkstra(p).search(end));
//        Algorithm.SearchResult search = Hipster.createDijkstra(p).search(end);
//
//
//
//        System.out.println(search);
//        return null;
//    }
//
//
//
//}
