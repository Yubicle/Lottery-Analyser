/**
 * Program Name:	LotteryPrizesAnalyzer.java
 * Purpose: 		To read a file specified by the user, calculate the prize allocation 
 * 					for each winner in each bracket, and display the winning numbers,
 * 					the amount of winners in each prize bracket and their prize allocation if 
 * 					there are multiple winners in one bracket.
 * @author			Alokwe Udueshi Miracle, 1190195
 * Date				14 Nov 2023
 */


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class U_A_LotteryPrizesAnalyzer
{
	public static void main(String[] args)
	{
		int prizePool; //prize pool		
		double FIRST_PLACE_PRIZE = 0.85, SECOND_PLACE_PRIZE = 0.07, THIRD_PLACE_PRIZE = 0.08; //prize percentages across tiers
		String lotteryName = ""; //lottery name
		String fileName;	//file name
		int numTickets = 0; //counts how many tickets are in the file/lottery
		
		// create scanner and file objects
		Scanner userInput = new Scanner(System.in); //input1 reads only user input
		Scanner fileInput = null; //reads from the file
		File fileReader; //file object 
		
		// data structures to store winnings numbers etc
		int[] winningNumbers;
		ArrayList<int[]> secondPrize = new ArrayList<>();
        ArrayList<int[]> thirdPrize = new ArrayList<>();
        int grandPrizeWinnerCount = 0; //counter for how many grand prize winners/tickets exist
		
		// display title and get user input for each prompt
		System.out.print("Lottery Prizes Analyzer\n\n");
		System.out.print("Enter the name of the lottery: ");
		lotteryName = userInput.nextLine();
		
		System.out.print("Enter the amount of money in the prize pool: $");
		
		// validate user input for correct minimum amount and correct data type
		while(!userInput.hasNextInt())
		{
			userInput.next();
			System.out.print("Invalid input. Please enter a number: ");
		}
		prizePool = userInput.nextInt();
		while (prizePool < 1000)
		{
			System.out.print("Please enter a minimum amount of $1000: ");
			while(!userInput.hasNextInt())
			{
				userInput.next();
				System.out.print("Invalid input. Please enter a number: ");
			}
			prizePool = userInput.nextInt();
		}
		System.out.print("Enter the path for the data file: ");
		fileName = userInput.next();

		//try-catch block to handle a situation where the user inputs a file name that doesn't exist in that directory.
		try
		{
			fileReader = new File(fileName);	//assign the string entered as fileName to the File reader object.
			fileInput = new Scanner(fileReader);	//assign the File object to a Scanner object that reads from the file
		}
		catch (FileNotFoundException ex) //if the file isn't found the program will exit
		{
			System.out.printf("\nYour file was not found. Quitting! %s", ex.getMessage());
			System.exit(0);
		}
		
		//assign the first numbers on the file to this array.
		//subsequent arrays read from the file will check against this array for 1st, 2nd, and 3rd place winners.
		winningNumbers = U_A_ProjectMethods.getNextSeries(fileInput);
		
		int[] ticket;	//this array will store a ticket from the file in each iteration of the loop
		
		//the loop will stop executing when it reads that the file doesn't have more ticket numbers.
		while (fileInput.hasNextLine())
		{
			ticket = U_A_ProjectMethods.getNextSeries(fileInput);
			
			//check if it is a winning ticket
			if (U_A_ProjectMethods.countMatchingNumbers(ticket, winningNumbers) == ticket.length)
			{
				//grand prize counter
				grandPrizeWinnerCount++;
			}
			else
			{	
				//check if second or third prize winner
				int matchingNumbers = U_A_ProjectMethods.countMatchingNumbers(ticket, winningNumbers);
				if (matchingNumbers == ticket.length - 1)
				{
					//second prize winner
					secondPrize.add(ticket);
				}
				else if(matchingNumbers == ticket.length - 2)
				{	
					//third prize winner
					thirdPrize.add(ticket);
				}
			}
			numTickets++;
		}//end of while loop
		
		
		//output the report to the user
		System.out.println("\nLottery Prizes Report");
		System.out.println("----------------------\n");
		
		System.out.printf("Lottery Name: %11s%s\n","", lotteryName);
		System.out.printf("Total prize pool: %8s%,d\n", "$",prizePool);
		System.out.printf("Number of tickets: %6s%,d\n","", numTickets);
		System.out.printf("Winning numbers: %8s%s\n", "", U_A_ProjectMethods.formatTicketNumbers(winningNumbers));
		
		//Display the grand prize winner(s)' information
		System.out.printf("\nGrand Prize Winners (all numbers match)...\n");
		System.out.printf("  Number of winners: %,5d\n", grandPrizeWinnerCount);
		System.out.printf("  %% of prize pool: %10s\n", "85.0");
		System.out.printf("  Total prize value: %5s%,.2f\n", "$", (prizePool * FIRST_PLACE_PRIZE));
		System.out.printf("  Prize per ticket: %6s%,.2f\n", "$",(double)((prizePool * FIRST_PLACE_PRIZE)/ grandPrizeWinnerCount));
		
		//Display the second prize winner(s)' information.
		System.out.printf("\nSecond prize winners (%d numbers match)...\n", winningNumbers.length - 1);
		System.out.printf("  Number of winners: %,5d\n", secondPrize.size());
		System.out.printf("  %% of prize pool: %9s\n", "7.0");
		System.out.printf("  Total prize value: %5s%,.2f\n","$", (prizePool * SECOND_PLACE_PRIZE));
		System.out.printf("  Prize per ticket: %6s%,.2f\n", "$", (double)((prizePool * SECOND_PLACE_PRIZE)/ secondPrize.size()));
		System.out.printf("  Ticket numbers: ");
		
		//output winning ticket numbers
		for (int i = 0; i < secondPrize.size(); i++)
		{
			System.out.printf("\t %s", U_A_ProjectMethods.formatTicketNumbers(secondPrize.get(i)));
			System.out.print("\t");
			if (i % 2 == 1)
			{
				//formatting
				System.out.println();
				System.out.print("\t\t");
			}
		}
		
		System.out.printf("\nThird prize winners (%d numbers match)...\n", winningNumbers.length - 2);
		System.out.printf("  Number of winners: %,6d\n", thirdPrize.size());
		System.out.printf("  %% of prize pool: %9s\n", "8.0");
		System.out.printf("  Total prize value: %5s%,.2f\n","$", (prizePool * THIRD_PLACE_PRIZE));
		System.out.printf("  Prize per ticket: %6s%,.2f\n","$", (double)((prizePool * THIRD_PLACE_PRIZE)/ thirdPrize.size()));
		System.out.printf("  Ticket numbers: ");
		
		//output winning ticket numbers
		for (int i = 0; i < thirdPrize.size(); i++)
		{
			System.out.printf("\t %s", U_A_ProjectMethods.formatTicketNumbers(thirdPrize.get(i)));
			System.out.print("\t");
			if (i % 2 == 1)
			{
				//formatting
				System.out.println();
				System.out.print("\t\t");
			}
		}
		// close the scanner object
		userInput.close();
	} //End of main method
} //End of class