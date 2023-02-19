//package com.FlightSearch.FlightSearch.model;
//
//import java.sql.Time;
//import java.time.LocalDateTime;
//
//public class FlightDto {
//    private String flightNumber;
//    private String departureTo;
//    private String arrivalTo;
//    private LocalDateTime departureDate;
//    private LocalDateTime arrivalDate;
//    private Time gateClosedTime;
//
//    public FlightDto() {
//    }
//
//    public FlightDto flightToFlightDto(Flight flight){
//        var result = new FlightDto();
//        result.setFlightNumber(flight.getFlightNumber());
//        result.setDepartureTo(flight.getDepartureTo());
//        result.setArrivalTo(flight.getArrivalTo());
//        result.setDepartureDate(flight.getDepartureDate());
//        result.setArrivalDate(flight.getArrivalDate());
//        result.setGateClosedTime(Time.valueOf(flight.getDepartureDate().toLocalTime().minusHours(1)));
//        return result;
//    }
//
//    public String getFlightNumber() {
//        return flightNumber;
//    }
//
//    public void setFlightNumber(String flightNumber) {
//        this.flightNumber = flightNumber;
//    }
//
//    public String getDepartureTo() {
//        return departureTo;
//    }
//
//    public void setDepartureTo(String departureTo) {
//        this.departureTo = departureTo;
//    }
//
//    public String getArrivalTo() {
//        return arrivalTo;
//    }
//
//    public void setArrivalTo(String arrivalTo) {
//        this.arrivalTo = arrivalTo;
//    }
//
//    public LocalDateTime getDepartureDate() {
//        return departureDate;
//    }
//
//    public void setDepartureDate(LocalDateTime departureDate) {
//        this.departureDate = departureDate;
//    }
//
//    public LocalDateTime getArrivalDate() {
//        return arrivalDate;
//    }
//
//    public void setArrivalDate(LocalDateTime arrivalDate) {
//        this.arrivalDate = arrivalDate;
//    }
//    public Time getGateClosedTime() {
//        return gateClosedTime;
//    }
//
//    public void setGateClosedTime(Time gateClosedTime) {
//        this.gateClosedTime = gateClosedTime;
//    }
//}
