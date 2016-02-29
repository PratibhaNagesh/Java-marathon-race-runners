package com.ucsc.java.finalterm;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 *	This class connects to the XML file specified by the user and gets every runner details
 */
public class RunnersXMLFile implements RaceDAO
{
	private Path xmlFilePath = null;
	private File xmlFile = null;
	private ArrayList<Runner> runners = null;
	
	
	/* (non-Javadoc)
	 * @see com.ucsc.java.finalterm.RaceReader#getRunnerDetails(java.lang.String)
	 */
	@Override
	public ArrayList<Runner> getRunnerDetails(String fileName)
	{
		// Check if file has been read already, do not read it again.
		if(runners != null)
			return runners;
		
		xmlFilePath = Paths.get(fileName);
		xmlFile = xmlFilePath.toFile();
		
		Runner r = null;
		runners = new ArrayList<>();
		
		// Check if file
		if(Files.exists(xmlFilePath))
		{
			// Create XMLInputFactory object
			XMLInputFactory inputFactory = XMLInputFactory.newFactory();
			try
			{
				// Create XMLStreamReader object
				FileReader fileReader =	new FileReader(xmlFile);
				XMLStreamReader reader = inputFactory.createXMLStreamReader(fileReader);
				
				// Read products from the XML file
				while (reader.hasNext())
				{
					int eventType = reader.getEventType();
					switch (eventType)
					{
						case XMLStreamConstants.START_ELEMENT: // set the state (i.e. name, speed and rest percentage) of each Runner.
							String elementName = reader.getLocalName();
							if (elementName.equals("Runner")) // sets runner name
							{
								r = new Runner();
								String name = reader.getAttributeValue(0);
								r.setRunnerName(name);
							}
							if (elementName.equals("RunnersMoveIncrement")) // sets runner speed
							{
								int speed = Integer.parseInt(reader.getElementText());
								r.setSpeed(speed);
							}
							if (elementName.equals("RestPercentage")) // sets rest percentage 
							{
								int restPercentage = Integer.parseInt(reader.getElementText());
								r.setRestPercentage(restPercentage);
							}
							break;
							
						case XMLStreamConstants.END_ELEMENT:
							
							elementName = reader.getLocalName();
							if (elementName.equals("Runner"))
							{
								runners.add(r); // add each runner object to the ArrayList
							}
							break;
							
						default:
							break;
					}
					reader.next(); // reads next value (runner) from the XML file
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
