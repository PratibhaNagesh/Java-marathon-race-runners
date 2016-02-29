package com.ucsc.java.finalterm.util;

import java.util.Scanner;

/**
 * This class validates the user inputs.
 */
public class Validator 
{
	/**
	 * Mandates user to enter String value and returns the value entered.
	 * 
	 * @param prompt requests user input
	 * @param sc Scanner object
	 * @return string input by the user
	 */
	
	public static String getRequiredString(String prompt, Scanner sc) 
	{
		String s = "";
		boolean isValid = false;
		while (isValid == false)
		{
			System.out.print(prompt);
			s = sc.nextLine();
			if (s.equals("")) 
			{
				System.out.println("Error! This entry is required. Try again. \n");
			}
			else
			{
				isValid = true;
			}
		}
		return s;
	}
	
	/**
	 * Mandates user to enter a integer value and returns it.
	 * 
	 * @param sc Scanner object
	 * @param prompt requests user input
	 * @return valid integer value entered by the user
	 */
	
	public static int getInt(Scanner sc, String prompt)  
	{
		int number = 0;
		boolean isValid = false;
		while (isValid == false) 
		{
			System.out.print(prompt);
			if (sc.hasNextInt())
			{
				number = sc.nextInt();
				isValid = true;
			}
			else
			{
				sc.next();
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine();
		}
		return number;
	}
	

	/**
	 * Mandate user to enter a integer value within the specified range and returns the value.
	 * 
	 * @param sc Scanner Object
	 * @param prompt requests user input
	 * @param min user should enter integer value greater than min
	 * @param max user should enter integer value less than max
	 * @return valid integer value entered by the user
	 */
	public static int getIntWithinRange(Scanner sc, String prompt, int min, int max) 
	{
		int number = 0;
		boolean isValid = false;
		
		while(isValid == false) 
		{
			number = getInt(sc, prompt);
			
			if (number < min) 
			{
				System.out.println("Error! Number must be greater than " + (min - 1));
			}
			else if (number > max) 
			{
				System.out.println("Error! Number must be less than " + (max + 1));
			}
			else 
			{
				isValid = true;
				break;
			}
		}
		return number;
	}

}
