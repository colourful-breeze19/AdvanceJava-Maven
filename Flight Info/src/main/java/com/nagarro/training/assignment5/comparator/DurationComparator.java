package com.nagarro.training.assignment5.comparator;
import java.util.Comparator;

import com.nagarro.training.assignment5.flight.*;
public class DurationComparator implements Comparator<FlightInfo> {

	public int compare(FlightInfo flight1, FlightInfo flight2) {
		// TODO Auto-generated method stub
		return (int) (flight1.getDuration() - flight2.getDuration());
	}

}
