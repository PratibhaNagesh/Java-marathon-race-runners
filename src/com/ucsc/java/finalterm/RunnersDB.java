package com.ucsc.java.finalterm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class connects to the Database and retrieves all runner details
 */
public class RunnersDB implements RaceDAO
{
	ArrayList<Runner> runners = null;
	/**
	 * This method connects to the database and returns the connection object
	 * 
	 * @param dbName name of the database the application has to connect to.
	 * @return the connection object.
	 */
	private Connection getConnection(String dbName)
	{
		Connection connection = null;
		
		try
		{
			String dbDir = "resources";
			System.setProperty("derby.system.home", dbDir);
			
			String url = "jdbc:derby:" + dbName.trim();
			String user = "";
			String password = "";
			connection = DriverManager.getConnection(url, user, password);

		}
		catch (SQLException e)
		{
			System.out.println();
		}
		return connection;
	}
	
	/**
	 * This method queries the database table for the runner details and returns the list of runner objects.
	 * 
	 * @param dbName name of the database which contains the runner details
	 * @return list of runner objects retrieved from the database table
	 */
	
	@Override
	public ArrayList<Runner> getRunnerDetails(String dbName)
	{
		try
		{
			Connection connection = getConnection(dbName);
			runners = new ArrayList<>();
			
			// query the runner table
			String query = "SELECT RunnerName, RunnerSpeed, RestPercentage "
						 + "FROM Runners";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				String runnerName = rs.getString("RunnerName");
				int runnerSpeed = rs.getInt("RunnerSpeed");
				int restPercentage = rs.getInt("RestPercentage");
				
				Runner r = new Runner();
				r.setRunnerName(runnerName);
				r.setSpeed(runnerSpeed);
				r.setRestPercentage(restPercentage);
				
				runners.add(r);
			}
			rs.close();
			ps.close();
			connection.close();
			
		}
		catch (Exception e)
		{
			return null;
		}
		return runners;
	}

}
