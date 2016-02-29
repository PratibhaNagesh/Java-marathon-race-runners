package com.ucsc.java.finalterm;

/**
 * 
 * This class runs thread for each runner object and simulates the race.
 */
public class ThreadRunner extends Thread
{
	private String name;
	private double speed;
	private int restPercentage;
	private RaceApp app;
	boolean firstThread;

	private static final int REST_TIME = 200;
	private static final int STEP_TIME = 100;
	private static final int TOTAL_RACE_DISTANCE = 1000;
	
	
	/**
	 * This constructor sets name, speed, rest percent of every runner and application object.  
	 * 
	 * @param name of the runner thread to be started
	 * @param speed of the runner thread to be started
	 * @param restPercentage of the runner thread to be started
	 * @param app object of the application class
	 */
	ThreadRunner(String name, double speed, int restPercentage, RaceApp app)
	{
		this.name = name;
		this.speed = speed;
		this.restPercentage = restPercentage;
		this.app = app;
	}
	
	/**
	 * This method returns the name of the runner thread
	 * 
	 * @return name of the runner thread
	 */
	public String getRunnerName(){
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		int distanceCovered = 0;
		
		try
		{
			while (distanceCovered < TOTAL_RACE_DISTANCE) // loops until the runner reaches the total race distance
			{
				int randomNumber = (int) (Math.random()*100 + 1);

				if (randomNumber <= restPercentage) 
				{
					Thread.sleep(REST_TIME);
				}
				else
				{
						distanceCovered += speed; 
						System.out.println(name + " : " + distanceCovered);
				}
				Thread.sleep(STEP_TIME);
			} 	

			// returns 'true' for the first thread calling isFirst method, returns 'false' for all other threads.
			firstThread = app.isFirst(); 
			
			if (firstThread == true) // only winner thread enters if block
			{
				System.out.println(name + " : I finished! \n");
				app.announceWinner(name);
				app.interruptThreads(name);
				return;
			}
			else // other loser threads sleep for a while, to allow for the announcement of the winner.
			{
				Thread.sleep(REST_TIME); 
			}
		}
		catch (InterruptedException e) 
		{
			// Empty catch 
		}
		System.out.println(name + " : You beat me fair and square."); // loser threads display this message.
	}
}
