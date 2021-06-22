/**
 * @class  FlightInfo
 * @author chahat
 * @description  FlightInfo class contains all the data used in the project.
 *
 */
package com.nagarro.training.assignment5.flight;

import com.nagarro.training.assignment5.constant.*;

public class FlightInfo {
	private String flightNum, departLoc, arrivalLoc, date, flightClass;
	private int fare;
	private float duration;

	public FlightInfo(String flightNum, String depLoc, String arrLoc, String date, int fare, float duration,
			String flightClass) {
		this.flightNum = flightNum;
		this.departLoc = depLoc;
		this.arrivalLoc = arrLoc;
		this.date = date;
		this.fare = fare;
		this.duration = duration;
		this.flightClass = flightClass;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public String getDepartLoc() {
		return departLoc;
	}

	public String getArrivalLoc() {
		return arrivalLoc;
	}

	public String getDate() {
		return date;
	}

	/**
	 * Method to calculate the flight fare. 
	 * basic fare for the economic and 40% extra fare for the business
	 * class.
	 *
	 * @return
	 */
	public int getFare() {
		int flightFare = this.fare;
		if (this.flightClass.equals(Constants.FLIGHT_BUSINESS))
			flightFare = flightFare + (int) (Constants.FLIGHT_FARE * flightFare);
		return flightFare;
	}

	public float getDuration() {
		return duration;
	}

	public String getFlightClass() {
		return flightClass;
	}

}
