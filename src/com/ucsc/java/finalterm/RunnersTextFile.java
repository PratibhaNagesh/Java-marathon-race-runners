package com.ucsc.java.finalterm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * This class connects to the Text file and gets every runner details
 */
public class RunnersTextFile implements RaceDAO
{
	private Path textFilePath = null;
	private File textFile = null;
	private ArrayList<Runner> runners = null;
	

	/* (non-Javadoc)
	 * @see com.ucsc.java.finalterm.RaceReader#getRunnerDetails(java.lang.String)
	 */
	@Override
	public ArrayList<Runner> getRunnerDetails(String fileName) 
	{
		if (runners != null)
			return runners;
		
		textFilePath = Paths.get(fileName);
		textFile = textFilePath.toFile();
		
		runners = new ArrayList<>();
		
		if(Files.exists(textFilePath))
		{
			try (BufferedReader in = new BufferedReader(
									 new FileReader(textFile)))
			{
				//String line = in.readLine();
				String line = null;
				while ((line = in.readLine()) != null) // read until the end of the text file
				{
					StringTokenizer t = new StringTokenizer(line, "\t");
					String name = t.nextToken();
					int speed = Integer.parseInt(t.nextToken());
					int restPercentage = Integer.parseInt(t.nextToken());
					
					Runner r = new Runner();
					r.setRunnerName(name);
					r.setSpeed(speed);
					r.setRestPercentage(restPercentage);
					
					// add runner to the ArrayList
					runners.add(r);
				}
			}
			catch (Exception e)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	
		return runners;
	}
}
