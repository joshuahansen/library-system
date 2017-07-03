package Driver;
//Main driver class, runs library
import java.io.IOException;
import java.util.Scanner;

import Holding.Holding;
import Member.Member;

public class MainDriver {

	public static void main(String[] args) throws IOException
	{
		Holding [] holdings = new Holding[15];					//array of Holdings
		Member [] members = new Member[15];						//array of members
		DateTime todaysDate= new DateTime();					//create a DateTime object of todays date
		DateTime returnDate = new DateTime(30, 6, 2016);			//change to check if overdue fees work 
		Library library = new Library();						//create a library object
						
		library.loadFile(holdings, members);					//auto load last state of library
		library.Menu();
		System.out.println("\n"+todaysDate.getFormattedDate());
//		library.defaultHoldings(holdings);									//uncomment to set up program with default users and holdings, is not used as loads from file
//		library.defaultMembers(members);
	
		Scanner keyboard_input = new Scanner(System.in);
		System.out.println("\nEnter an Option:");
		int input = keyboard_input.nextInt();
		do									//continue running program until user chooses to exit
		{
			library.Options(input,holdings,members,todaysDate, returnDate);
	
			library.Menu();
			System.out.println("\nEnter an Option:");
			input = keyboard_input.nextInt();
		}while(input != 13);
		library.exitSystem(holdings, members);
		keyboard_input.close();
	}

}
