package com.ucsc.java.finalterm;

import java.util.ArrayList;
import java.util.Scanner;

import com.ucsc.java.finalterm.util.Validator;

/**
 * RaceApp class simulates the Marathon race between interested group of participants.
 */

public class RaceApp 
{
	private ArrayList<Runner> runners = null;
	private static RaceDAO raceDAO = null;
	ArrayList<Thread> threadList = null;
	boolean executed = false;
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		String fileName = "";
		String dbName = "";
		RaceApp app = new RaceApp();
		
		int choice = 0;
		while (choice != 5) // loops until user selects option 5 i.e. Exit
		{
			app.reset(); // reset 'executed' boolean variable to false before simulating a new race
			
			System.out.println("Welcome to the Marathon Race Runner Program. \n");
			
			// Presents data source option for the user to select
			System.out.println("Select your data source: \n");
			System.out.println("1. Derby database \n2. XML File \n3. Text File \n4. Default two runners \n5. Exit \n");
			
			// Get valid user input
			choice = Validator.getIntWithinRange(sc, "Enter your choice: ", 1, 5);
			System.out.println();
			
			// Get object of the class which connects to the data source
			raceDAO = DAOFactory.getRaceDAO(choice);
			
			// Switch as per user choice and get name of the data source.
			switch (choice)
			{
				case 1: dbName = Validator.getRequiredString("Enter Database name: ", sc);
						app.createThreads(dbName);
						break;
						
				case 2: fileName = Validator.getRequiredString("Enter XML file name: ", sc);
						app.createThreads(fileName);
						break;
							
				case 3:	fileName = Validator.getRequiredString("Enter Text file name: ", sc);
						app.createThreads(fileName);
						break;
						
				case 4: app.createThreads("");
						break;
						
				case 5: System.out.println("Thank you for using my Marathon Race Program"); 
						System.exit(0);
						
				default: break;
			}
		}
	}
	
	public void reset()
	{
		executed = false; // Reset the boolean variable (executed) before simulating new race.
	}
	
	/**
	 * This method accesses the runners in the file and spawns separate thread for each runner.
	 * @param fileName is the file which contains the details of the runners
	 */
	public void createThreads(String fileName)
	{
		// Get all the runners from the data source
		if (fileName.equals(""))
		{
			DefaultRunners defaultRunners = (DefaultRunners) raceDAO;
			runners = defaultRunners.getRunnerDetails();
		}
		else
			runners = raceDAO.getRunnerDetails(fileName);
		
		if (runners == null) // if null re-enter the data source name
		{
			System.err.println("Error! Unable to get runners. Please re-enter valid data source.\n");
		}
		else 
		{
			int numberOfRunners = runners.size();
			threadList = new ArrayList<>();
			ThreadRunner thread = null;
			
			System.out.println("Get set...Go!");
			
			// start thread for each runner
			for (int i = 0; i < numberOfRunners; i++)
			{
				String name = runners.get(i).getRunnerName();
				int speed = runners.get(i).getSpeed();
				int restPercentage = runners.get(i).getRestPercentage();
				
				// Create thread for each Runner
				thread = new ThreadRunner(name, speed, restPercentage, this);
				thread.start(); 
				threadList.add(thread);
			}
	
			waitOnThreadsToComplete(); // main thread waits until other threads complete
			
			System.out.println();
			
			pressEnterToContinue(); // key enter to proceed
		}
	}
	
	/**
	 * Thread which finishes first has access to this method
	 * @return true once the method is called.
	 */
	public synchronized boolean isFirst()
	{
		if (executed == false)
		{
			executed = true; // only winner thread can set executed to true
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method announces the race completion and the name of winner thread
	 * @param threadName winner thread name
	 */
	public void announceWinner(String threadName)
	{
		System.out.println("The race is over! The " + threadName + " is the winner. \n");
	}
	
	/**
	 * This method interrupts the other threads once the winner thread is found
	 * @param threadName winner thread name
	 */
	public void interruptThreads(String threadName)
	{
		for (int i = 0; i < threadList.size(); i++)
		{
			if (!threadName.equals(((ThreadRunner) threadList.get(i)).getRunnerName()))
			{
				threadList.get(i).interrupt(); // interrupts other threads when one runner(thread) completes the race.
			}
		}
	}
	
	/**
	 * Main thread waits until all threads finish execution
	 */
	private void waitOnThreadsToComplete()
	{
		for (int i = 0; i < threadList.size(); i++)
		{
			try 
			{
				threadList.get(i).join();
				//System.out.println(((ThreadRunner) threadList.get(i)).getRunnerName()	 + " finished. ");
			}
			catch (InterruptedException e)	{}
		}
	}
	
	 
	/**
	 *  Press enter key to continue
	 */
	private void pressEnterToContinue()
	 { 
	        System.out.println("Press enter to continue . . .");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
}
