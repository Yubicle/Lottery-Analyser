/**
 * Program Name:	UA_ProjectMethods.java
 * Purpose: 		Methods called in the main method .class file. 
 * 					getNextSeries to read the file, 
 * 					countMatchingNumbers to find winners
 * 					formatTicketNumbers to make the winning numbers readable for the user.
 * @author			Alokwe Udueshi Miracle, 1190195
 * Date				18 Nov 2023
 */


import java.util.Scanner;

public class U_A_ProjectMethods
{
	/**
	 *Method Name: 	getNextSeries
	 *Purpose: 		To create an int array from a file 
	 *				read with a Scanner object and remove 
	 *				comma separators between elements. 
	 *Accepts:		Scanner object that reads from a file
	 *Returns:		int[] after being parsed from a String array's elements 
	 */
	public static int[] getNextSeries(Scanner scanner)
	{
		String[] seriesString;
		
		seriesString = scanner.nextLine().split(",");
		
		int[] seriesInts = new int[seriesString.length];
		
		for (int i = 0; i < seriesString.length; i++)
		{
			seriesInts[i] = Integer.parseInt(seriesString[i]);
		}		
		return seriesInts;
	}
	
	/**
	 *Method Name: 	countMatchingNumbers
	 *Purpose: 		Compare a ticket to the winning numbers and tracks 
	 *				how many matches there are
	 *Accepts:		2 int arrays; one ticket and the winning numbers
	 *Returns:		int that is a count of how many matches there are
	 *				between both arrays.
	 */
	public static int countMatchingNumbers(int[] ticket, int[] win)
	{
		int count = 0;
		for (int i = 0; i < win.length; i++)
		{
			for (int j = 0; j < ticket.length; j++)
			{
				if (ticket[j] == win[i])
				{
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 *Method Name: 	formatTicketNumbers
	 *Purpose: 		To separate each number on a ticket with 
	 *				commas for easy viewing by the user
	 *Accepts:		int[] (an int array of ticket numbers)
	 *Returns:		String of the elements in the array with 
	 *				commas only between them.
	 */
	public static String formatTicketNumbers(int[] ticket)
	{
		String result = "";
		for (int i = 0; i < ticket.length; i++) {
            result += ticket[i];

            // Add a comma if it's not the last element
            if (i < ticket.length - 1) {
                result += ", ";
            }
        }
		return result;
	}
	
	
	
	
}	
//End of class