package com.ucsc.java.finalterm;

/**
 *  
 * This factory class returns the reference to the object of the class which connects to the 
 * data source as selected by the user.
 */
public class DAOFactory 
{
	public static RaceDAO getRaceDAO(int choice)
	{
		RaceDAO raceDAO = null;
		
		if (choice == 1)
		{
			raceDAO = new RunnersDB(); // The object of this class to Database
		}
		else if (choice == 2)
		{
			raceDAO = new RunnersXMLFile(); // The object of this class connects to the XML file
		}
		else if (choice == 3)
		{
			raceDAO = new RunnersTextFile(); // The object of this class connects to the Text file
		}
		else if (choice == 4)
		{
			raceDAO = new DefaultRunners(); // The object of this class has 2 default runners
		}
		return raceDAO;
	}
}
