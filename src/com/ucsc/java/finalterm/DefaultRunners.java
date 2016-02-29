package com.ucsc.java.finalterm;

import java.util.ArrayList;

/**
 * 
 * This class returns details of two default runners 
 */
public class DefaultRunners implements RaceDAO
{
	ArrayList<Runner> runners = new ArrayList<>();
	
	@Override
	public ArrayList<Runner> getRunnerDetails(String fileName) 
	{
		return null;
	}
	
	
	/**
	 * @return list of 2 default runners
	 */
	public ArrayList<Runner> getRunnerDetails()
	{		
		// Set Runner 1 details and add Runner object to ArrayList
		Runner r1 = new Runner();
		r1.setRunnerName("Tortoise");
		r1.setSpeed(10);
		r1.setRestPercentage(0);
		
		runners.add(r1);
		
		// Set Runner 2 details and add Runner object to ArrayList
		Runner r2 = new Runner();
		r2.setRunnerName("Hare");
		r2.setSpeed(100);
		r2.setRestPercentage(90);
		
		runners.add(r2);
		
		return runners;		
	}

}
