/**
 * @class FlightView
 * @author chahat
 * @description FlightView class display the output flight list that contains all the filtered results.
 *
 */
package com.nagarro.training.assignment5.view;

import java.util.ArrayList;

import com.nagarro.training.assignment5.constant.Constants;
import com.nagarro.training.assignment5.flight.FlightInfo;

public class FlightView {
   
    public void viewFlights(ArrayList<FlightInfo> flightList) {
        System.out.println("\n \t\t ********AVAILABLE FLIGHTS********\n");
        System.out.println("FLIGHT NO|DEP LOC|ARR LOC|VALID TILL|FARE|DURATION|FLIGHT CLASS");

        for (FlightInfo f : flightList) {
            System.out.print(" " + f.getFlightNum());
            System.out.print(Constants.SEPARTATOR2 + f.getDepartLoc());
            System.out.print(Constants.SEPARTATOR2 + f.getArrivalLoc());
            System.out.print(Constants.SEPARTATOR2 + f.getDate());
            System.out.print(Constants.SEPARTATOR + f.getFare());
            System.out.print(Constants.SEPARTATOR  + f.getDuration());
            System.out.println(Constants.SEPARTATOR3+ f.getFlightClass());
        }
        if (flightList.isEmpty()) {
            System.out.println(Constants.FLIGHT_NOT_AVAILABLE);
        }
    }
}
