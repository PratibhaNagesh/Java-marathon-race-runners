package com.ucsc.java.finalterm;

/**
 *	Runner class which stores name, speed and rest percentage for every runner object.
 */
public class Runner 
{
	private String runnerName;
	private int speed;
	private int restPercentage;
	
	/**
	 * @return the name of the runner
	 */
	public String getRunnerName() {
		return runnerName;
	}
	
	/**
	 * @param runnerName set the runner name
	 */
	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}
	
	/**
	 * @return the runner speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * @param speed sets the runner speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * @return the runner rest percentage i.e. how much runner has to rest
	 */
	public int getRestPercentage() {
		return restPercentage;
	}
	
	/**
	 * @param restPercentage sets the runner rest percentage
	 */
	public void setRestPercentage(int restPercentage) {
		this.restPercentage = restPercentage;
	}	
}
