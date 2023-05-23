package com.FlightSearch.FlightSearch.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class WeightFlightsPath implements Comparable<WeightFlightsPath> {
    private List<Long> ids;
    private BigDecimal sumOfPrices;

    public WeightFlightsPath(List<Long> ids, BigDecimal sumOfPrices) {
        this.ids = ids;
        this.sumOfPrices = sumOfPrices;
    }


    @Override
    public int compareTo(WeightFlightsPath o) {
        return this.sumOfPrices.compareTo(o.getSumOfPrices());
    }
}
