/**
 * @class  FlightParser
 * @author chahat
 * @description FlightParser class filter the data from the airline files as per user requirements.
 *
 */
package com.nagarro.training.assignment5.view;

import com.nagarro.training.assignment5.constant.Constants;
import com.nagarro.training.assignment5.flight.FlightInfo;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;
import java.util.concurrent.Callable;

public class FlightParser implements Callable<ArrayList<FlightInfo>> {
    /**
     * Declared variables to store the required data to search the flights.
     */
    String filename, dept_loc, arrival_loc, date, flightClass;

    public FlightParser(String filename, String dept_loc, String arrival_loc, String date, String flightClass) {
        this.filename = filename;
        this.dept_loc = dept_loc;
        this.arrival_loc = arrival_loc;
        this.date = date;
        this.flightClass = flightClass;
    }

    /**
     * Method to search the flights and update them in the flightList list.
     */
    public ArrayList<FlightInfo> call() throws Exception {
        ArrayList<FlightInfo> flightList = new ArrayList<FlightInfo>();
        /**
         * Used a third party package OpenCSV for reading the CSV files for the airlines.
         */
        CSVReader reader = new CSVReader(new FileReader(filename));
        String[] nextLine;
        ArrayList<String> arr;

        /**
         * Loop through each line of the csv file to extract the flight data.
         */
        while (true) {
            /**
             * Base Condition: If the file has no more line then loop ends.
             */
            if (!((nextLine = reader.readNext()) != null)) break;

            /**
             * Generate token out of each line and use them to search the required data.
             */
            for (String line : nextLine) {
                StringTokenizer tokenList = new StringTokenizer(line,Constants.SEPARTATOR);
                arr = new ArrayList<String>(line.length());
                while (tokenList.hasMoreTokens()) {
                    arr.add(tokenList.nextToken());
                }
                if (arr.get(1).equals(dept_loc) && arr.get(2).equals(arrival_loc) && arr.get(3).equals(date) && arr.get(8).matches(flightClass + "|EB") && arr.get(7).equals("Y")) {
                    FlightInfo model = new FlightInfo(arr.get(0), arr.get(1), arr.get(2), arr.get(3), Integer.parseInt(arr.get(6)), Float.parseFloat(arr.get(5)), flightClass);
                    flightList.add(model);
                }
            }
        }
        return flightList;
    }
}
