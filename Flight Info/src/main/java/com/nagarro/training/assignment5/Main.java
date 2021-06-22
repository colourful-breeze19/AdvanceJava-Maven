/**
 * @class Main
 * @author chahat
 * @description Main class search the flights from all the available airline data.
 *
 */
package com.nagarro.training.assignment5;

import com.nagarro.training.assignment5.flight.FlightInfo;
import com.nagarro.training.assignment5.flight.FlightSearch;
import com.nagarro.training.assignment5.flight.InputValidator;
import com.nagarro.training.assignment5.view.FlightView;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	public static void main(String[] args) throws IOException, CsvValidationException {

		final String depLoc;
		final String arrLoc;
		final String date;
		final String flightClass;
		final int outputPreference;

		/**
		 * Input required data from the user and store in their respective variables.
		 * Use of InputValidator class for all the input validations
		 */
		depLoc = InputValidator.depLocationInput();
		arrLoc = InputValidator.arrlocationInput();
		date = InputValidator.enterDate();
		flightClass = InputValidator.flightClassInput();
		outputPreference = InputValidator.output();

		/**
		 * Used Timer to update the search results in fixed time interval.
		 */
		final Timer t = new Timer();
		class MyTask extends TimerTask {
			int counter = 0;

			public void run() {
				counter += 1;
				FlightSearch fetchFlightDetails = new FlightSearch();
				ArrayList<FlightInfo> flightDetails = fetchFlightDetails.getFlightList(depLoc, arrLoc, date,
						flightClass, outputPreference);

				if (counter == 10) {
					t.cancel();
				} else {
					FlightView display = new FlightView();
					display.viewFlights(flightDetails);
				}
			}
		}
		TimerTask task = new MyTask();
		t.schedule(task, 0, 30000);
	}
}
