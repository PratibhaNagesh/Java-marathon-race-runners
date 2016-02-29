package com.ucsc.java.finalterm;

import java.util.ArrayList;

/**
 * This interface has a single abstract method 
 */
public interface RaceReader 
{
	/**
	 * Abstract method definition which takes filename as input and returns a list of runner object.
	 *  
	 * @param fileName file used to retrieve runner details
	 * @return List of
	 */
	public ArrayList<Runner> getRunnerDetails(String fileName);
}
