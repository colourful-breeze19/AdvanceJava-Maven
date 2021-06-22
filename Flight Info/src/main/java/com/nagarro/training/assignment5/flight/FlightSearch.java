/**
 * @class FlightSearch
 * @author chahat
 * @description FlightSearch class display the output flight list that contains all the filtered results.
 *
 */
package com.nagarro.training.assignment5.flight;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.nagarro.training.assignment5.comparator.DurationComparator;
import com.nagarro.training.assignment5.comparator.FareComparator;
import com.nagarro.training.assignment5.constant.Constants;
import com.nagarro.training.assignment5.view.FlightParser;


public class FlightSearch {

    public ArrayList<FlightInfo> getFlightList(String depLoc, String arrLoc, 
    		String date, String flightClass, int outputPreference) {
        File[] fileslist = GetCsv.getFiles();
        ExecutorService es = Executors.newFixedThreadPool(fileslist.length);

        /**
         * future array that  waits for the return value before executing.
         */
        @SuppressWarnings("unchecked")
        Future<ArrayList<FlightInfo>>[] results = new Future[fileslist.length];

        /**
         * Loop through the listOfFiles list and search for flights in each airline's data file.
         */
        for (int i = 0; i < fileslist.length; i++) {
            FlightParser fc = new FlightParser(fileslist[i].getPath(), depLoc, arrLoc, date, flightClass);
            results[i] = es.submit(fc);
        }

        /**
         * ArrayList to store all the searched flights for the user.
         */
        ArrayList<FlightInfo> flightDetails = new ArrayList<FlightInfo>();

        /**
         * Future array blocks the execution until return value is available.
         */
        for (Future<ArrayList<FlightInfo>> result : results) {
            try {
                ArrayList<FlightInfo> value = result.get();
                flightDetails.addAll(value);
            } catch (ExecutionException e) {
                System.out.print("Error in thread.");
            } catch (Exception e) {
            }
        }

        /**
         * Close the ExecutorService.
         */
        es.shutdown();

        /**
         * Return all the the flights.
         */
        updateView(outputPreference, flightDetails);
        return flightDetails;
    }

    /**
     * Method to update the flightList according to the output preference of the user.
     *
     * @param choiceCode
     * @param flightList
     */
    public static void updateView(int choiceCode, ArrayList<FlightInfo> flightList) {
        /**
         * Sort flight fare.
         */
        if (choiceCode == 1) {
            Collections.sort(flightList, new FareComparator());
        }
        /**
         * Sort flight duration.
         */
        else if (choiceCode == 2) {
            Collections.sort(flightList, new DurationComparator());
        } else {
            System.out.println(Constants.INVALID_CHOICE);
            return;
        }
    }
}
