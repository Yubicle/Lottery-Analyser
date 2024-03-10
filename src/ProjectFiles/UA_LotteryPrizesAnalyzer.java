/**
 * Program Name:	LotteryPrizesAnalyzer.java
 * Purpose: 		To read a file specified by the user, calculate the prize allocation for each winner in each bracket, and display the winning numbers
 * @author			Alokwe Miracle, 1190195
 * Date				14 Nov 2023
 */
package ProjectFiles;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import 

public class UA_LotteryPrizesAnalyzer
{
	public static void main(String[] args)
	{
		int prizePool; //constant variable for the prize pool in this 
		String lotteryName = "";
		String fileName;
		// create scanner and file objects
		Scanner input1 = new Scanner(System.in); //input1 reads only user input
		Scanner fileInput;
		File fileReader;
		
		// display title and get user input for each prompt
		
		System.out.print("Lottery Prizes Analyzer\n\n");
		System.out.print("Enter the name of the lottery: ");
		lotteryName = input1.nextLine();
		
		System.out.print("Enter the amount of money in the prize pool: $");
		
		// validate user input
		while(!input1.hasNextInt())
		{
			input1.next();
			System.out.print("Invalid input. Please enter a number: ");
		}
		prizePool = input1.nextInt();
		while (prizePool < 1000)
		{
			System.out.print("Please enter an amount over or equal to $1000: ");
			while(!input1.hasNextInt())
			{
				input1.next();
				System.out.print("Invalid input. Please enter a number: ");
			}
			prizePool = input1.nextInt();
		}
		System.out.print("Enter the path for the data file: ");
		fileName = input1.next();

		try
		{
			fileReader = new File(fileName);	//assign the string entered as fileName to the file reader object
			fileInput = new Scanner(fileReader);
		}
		catch (FileNotFoundException ex) //hehe copied this part from you professor :)
		{
			System.out.printf("\nYour file was not found. Quitting! %s", ex.getMessage());
			System.exit(0);
		}
		
		int[] winningNumbers;
		
		
				
		
	
		// close the scanner object
		input1.close();
	} //End of main method
} //End of class