package com.FlightSearch.FlightSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class FlightSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApplication.class, args);
//		LocalDateTime ldt1 = LocalDateTime.parse("2019-04-28T22:32:38.536");
//		LocalDateTime ldt2 = LocalDateTime.parse("2019-04-28T15:32:56.000");
//
//		int diff = ldt1.compareTo(ldt2);
//
//		System.out.println("Compare value = " + diff);	//2
//
//		if (diff > 0) {
//			System.out.println("ldt1 is later than ldt2");	//Prints it
//		} else if (diff < 0) {
//			System.out.println("ldt1 is earlier than ldt2");
//		} else {
//			System.out.println("both dates are equal");
//		}
	}

}
