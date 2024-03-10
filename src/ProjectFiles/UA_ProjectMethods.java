/**
 * Program Name:	UA_ProjectMethods.java
 * Purpose: 		Methods called in the main method .class file.
 * @author			Alokwe Udueshi Miracle, 1190195
 * Date				18 Nov 2023
 */
package ProjectFiles;

import java.util.Scanner;

public class UA_ProjectMethods
{
	/**
	 *Method Name: 	getNextSeries
	 *Purpose: 		To create an int array from a file read with a Scanner object
	 				and remove comma separators between elements. 
	 *Accepts:		Scanner object that reads from a file
	 *Returns:		int[] after being parsed from a String array's elements 
	 */
	public static int[] getNextSeries(Scanner scanner)
	{
		String[] seriesString = null;
		boolean isFirstRow = true;
		
		if (isFirstRow)
		{
			isFirstRow = false;
			seriesString = scanner.nextLine().split(",");
		}
		else if (scanner.hasNextLine())
		{
			seriesString = scanner.nextLine().split(",");
		}
		
		int[] seriesInts = new int[seriesString.length];
		
		for (int i = 0; i < seriesString.length; i++)
		{
			seriesInts[i] = Integer.parseInt(seriesString[i]);
		}		
		return seriesInts;
	}
}
//End of class