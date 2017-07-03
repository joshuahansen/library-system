package Driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Exceptions.InsufficientCreditException;
import Exceptions.ItemInactiveException;
import Exceptions.MemberInactiveException;
import Exceptions.NoHoldingFoundException;
import Exceptions.NoMemberFoundException;
import Exceptions.OnLoanException;
import Holding.Book;
import Holding.Holding;
import Holding.Video;
import Member.Member;
import Member.PremiumMember;
import Member.StandardMember;

public class Library 
{
	private static int noBH = 0;								//number of book holdings
	private static int noVH = 0;								//number of video holdings
	private static int noH = noBH + noVH;						//number of holdings
	private static int noSM = 0;								//number of standard members
	private static int noPM = 0;								//number of premium members
	private static int noM = noSM + noPM;						//number of members
	private static boolean admin = false;						//admin boolean to check if user is admin
	
	
	protected static void Seperator()							//produce the border for the title

	{
		for(int i = 0; i<42;i++)
		{
			System.out.print("=");
		}
	}

	private void adminMenu()		//print admin menu
	{
		Seperator();
		System.out.print("\n||");
		System.out.print("\t\tAdministrator\t\t||\n");
		Seperator();
		System.out.println();
		System.out.println("Search holdings or members to activate or deactivate");		//functions admin can run
		System.out.println("Give members Credit by using 10. Search Menu option");
	}
	protected void Menu()
													//print the menu option
	{
		if(admin == true)			//if in admin mode print admin on top of menu
		{
			adminMenu();
		}
		Seperator();
		System.out.print("\n||");
		System.out.print("\tLibrary Management System\t||\n");
		Seperator();
		System.out.println("\n0.	Administrator");
		System.out.println("1.	Add Holding");
		System.out.println("2.	Remove Holding");
		System.out.println("3.	Add Memeber");
		System.out.println("4.	Remove Member");
		System.out.println("5.	Borrow Holding");
		System.out.println("6.	Return Holding");
		System.out.println("7.	Print all Holdings");
		System.out.println("8.	Print all Members");
		System.out.println("9.	Search Holdings");
		System.out.println("10.	Search Members");
		System.out.println("11.	Save to File");
		System.out.println("12.	Load from File");
		System.out.println("13.	Exit");
		Seperator();
	}
	
	protected void Options(int input, Holding[] holdings, Member[] members, DateTime dateTime, DateTime returnDate) throws IOException
	{
		switch(input)
		{
			case 0:				//administrator
			{
				runAsAdmin();		//run/exit admin mode
				break;
			}
			case 1:			//Enter new holding from user input
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tAdd Holdings\t\t||\n");
				Seperator();
				System.out.println();
				addHolding(holdings);
				break;
			}
			case 2:													//remove holding
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tRemove Holding\t\t||\n");
				Seperator();
				System.out.println();
				removeHolding(holdings);
				break;
			}
			case 3:																//create a new member
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tAdd Member\t\t||\n");
				Seperator();
				System.out.println();
				addMember(members);
				break;
			}
			case 4:
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tRemove Member\t\t||\n");
				Seperator();
				System.out.println();
				removeMember(holdings, members);
				break;
			}
			case 5:
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tBorrow Holding\t\t||\n");
				Seperator();
				System.out.println();
				borrowHolding(holdings, members, dateTime);
				break;
			}
			case 6:
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tReturn Holdings\t\t||\n");
				Seperator();
				System.out.println();
				returnHolding(holdings, members, returnDate);
				break;
			}
			case 7:	
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tAll Holdings\t\t||\n");
				Seperator();
				System.out.println();
				PrintHoldings(holdings);
				break;
			}
			case 8:
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tAll Members\t\t||\n");
				Seperator();
				System.out.println();
				PrintMembers(members, holdings);
				break;
			}
			case 9:															//search for holding via Title of holding
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tSearch Holdings\t\t||\n");
				Seperator();
				System.out.println();
				searchHoldings(holdings, members);
				break;
			}
			case 10:														//search for a member using there name
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tSearch Members\t\t||\n");
				Seperator();
				System.out.println();
				searchMembers(members, holdings);
				break;
			}
			case 11:													//save current state of library to file
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tSave File\t\t||\n");
				Seperator();
				System.out.println();
				saveFile(holdings,members);
				break;
			}
			case 12:												//load previous library state from file
			{
				if(admin == true)
				{
					adminMenu();
				}
				Seperator();
				System.out.print("\n||");
				System.out.print("\t\tLoad File\t\t||\n");
				Seperator();
				System.out.println();
				loadFile(holdings,members);
				break;
			}
			default:
			{
				System.out.println("Invalid input. Try again");
				break;
			}
		}
	}
	
	protected void defaultHoldings(Holding[] holdings)								//create default holdings objects and save them to the array
	{
		Holding h1 = new Book(1,"Intro to Java");
		holdings[0] = h1;
		Holding h2 = new Book(2,"Learning UML");
		holdings[1] = h2;
		Holding h3 = new Book(3,"Design Patterns");
		holdings[2] = h3;
		Holding h4 = new Book(4,"Advanced Java");
		holdings[3] = h4;
		Holding h5 = new Video(1,"Java 1",4);
		holdings[4] = h5;
		Holding h6 = new Video(2,"Java 2",6);
		holdings[5] = h6;
		Holding h7 = new Video(3,"UML 1",6);
		holdings[6] = h7;
		Holding h8 = new Video(4,"UML 2",4);
		holdings[7] = h8;
	}
	protected void defaultMembers(Member[] members)								//create default member objects and save them to the array
	{
		Member m1 = new StandardMember(1,"Joe Bloggs",false);
		members[0] = m1;
		Member m2 = new StandardMember(2,"Jane Smith",false);
		members[1] = m2;
		Member m3 = new PremiumMember(1,"Fred Bloggs",false);
		members[2] = m3;
		Member m4 = new PremiumMember(2,"Fred Smith",false);
		members[3] = m4;
	}
	
	private void runAsAdmin()													//enter/exit admin mode
	{
		if(admin == false)
		{
				admin = true;
		}
		else if(admin == true)
		{
			admin = false;
		}
	}
	private void addHolding(Holding[] holdings)									//add holding
	{
		System.out.print("Create a new holding? (y)yes/(n)no: ");
		Scanner newHolding = new Scanner(System.in);
		String newholding = newHolding.nextLine();
		while(newholding.equalsIgnoreCase("exit") != true && newholding.equalsIgnoreCase("e") != true && newholding.equalsIgnoreCase("y") == true)
		{
			System.out.print("Add Holdings Type (b) for Book or (v) for Video: ");				//choose if holding is a Book or Video
			Scanner user_input = new Scanner(System.in);
			String inputTitle;

			char holdingType = user_input.nextLine().charAt(0);						//exit loop
			if(holdingType == 'e')
			{
				break;
			}
			System.out.print("Enter Title of Holding: ");
			inputTitle = user_input.nextLine();				
			String title = inputTitle.substring(0, 1).toUpperCase() + inputTitle.substring(1);
			if(holdingType == 'b')					//get the first letter in the string to know if its a 
			{										//book or video
				int holdingID = noBH +1;
				Holding h1 = new Book(holdingID,title);		//create a new book object
				int writeHolding = 0;
				for(int i = 0; i < holdings.length; i++)				//print to the first empty space in the array
				{
					if( holdings[i] == null)
					{
						writeHolding = i;
						break;
					}
				}
				holdings[writeHolding] = h1;
				noBH++;
			}
			else if(holdingType == 'v')								//create a video holding and write it to the first empty space
			{
				int holdingID = noVH + 1;
				System.out.print("is the loan fee $4 or $6: ");
				int loanFee = user_input.nextInt();	
				if (loanFee == 4 || loanFee == 6)
				{
					Holding h1 = new Video(holdingID,title,loanFee);
					int writeHolding = 0;
					for(int i = 0; i < holdings.length; i++)
					{
						if( holdings[i] == null)
						{
							writeHolding = i;
							break;
						}
					}
					holdings[writeHolding] = h1;
					noVH++;
				}
				else
				{
					System.out.println("Incorrect loan fee");
				}
			}
			else 
			{
				System.out.println("Error incorrect Holding ID");		//print error message if the type of holding is incorrect
			}
			System.out.print("Create a new holding? (y)yes/(n)no: ");
			newholding = newHolding.nextLine();
		}
	}
	private void removeHolding(Holding[] holdings)								//remove holding
	{
		System.out.println("Remove Holding: ");
		System.out.print("Search by Holding ID or Title: ");
		Scanner keyboard= new Scanner(System.in);
		String search = keyboard.nextLine();
		boolean match = false;								//set match to false. used to check if a match is found
		if(search.equalsIgnoreCase("exit") != true && search.equalsIgnoreCase("e") != true)
		{
			try
			{
				int removeHolding = 0;
				for(int i = 0; i < holdings.length; i++)					//loop through the array 
				{
					if((search.charAt(0) == 'b' && search.charAt(1) == '0') || (search.charAt(0) == 'v' && search.charAt(1) == '0'))
					{
						if(holdings[i] != null && search.equalsIgnoreCase(holdings[i].getID()) == true)	//if the ID is equal to the one user is searching for save array position
						{
							removeHolding = i;
							match = true;											//match found set to true
							break;
						}
					}
					else if(holdings[i] != null && search.equalsIgnoreCase(holdings[i].getTitle()) == true)
					{
						removeHolding = i;
						match = true;											//match found set to true
						break;
					}
				}
				if(match == true)												//if match is found delete holding otherwise prompt user no match found
				{
					char delete;				
					System.out.print("Are you sure you want to delete "+search +" (y)yes/(n)no: ");		//confirmation that user wants to delete holding 
					delete = keyboard.next().charAt(0);
					if(delete == 'n' || delete == 'N')
					{
						System.out.println("Holding: "+search +" not deleted");			//if no don't delete
					}
					else if(delete == 'y' || delete == 'Y')									//if yes set the holding to null to remove holding from system
					{
						if(search.charAt(0) == 'b')
						{
							noBH--;
						}
						else
						{
							noVH--;
						}
						holdings[removeHolding] = null;
						System.out.println("HOLDING DELETED");
					}
				}
				else														//no match found
				{
					throw new NoHoldingFoundException();
				}
			}catch(NoHoldingFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
 	private void addMember(Member[] members)										//add Member
	{		
 		System.out.println("Create a new Member? (y)yes/(n)no: ");
		Scanner newMember = new Scanner(System.in);
		String newmember = newMember.nextLine();
		while(newmember.equals("exit") != true && newmember.equals("e") != true && newmember.equalsIgnoreCase("y") == true)
		{
			Scanner user_input = new Scanner(System.in);
	 		System.out.print("New Member Standard ('s')/Premium ('p'): ");
			
			char memberType = user_input.nextLine().charAt(0);
			
			if(memberType == 'e')
			{
				break;
			}
			
			System.out.print("Enter Users Full Name: ");					
			String inputName = user_input.nextLine();
			String fullName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);
			if(memberType == 'S' || memberType == 's')		//create a standard member
			{
				int memberId = noSM + 1;
				StandardMember m1 = new StandardMember(memberId,fullName,false);
				int writeMember = 0;
				for(int i = 0; i < members.length; i++)			//search array for first empty slot
				{
					if( members[i] == null)
					{
						writeMember = i;
						break;
					}
				}
				members[writeMember] = m1;						//write member into first empty slot
				noSM++;
			}
			else if(memberType == 'P' || memberType =='p')				//same for premium member as standard member
			{
				int memberId = noPM +1;
				PremiumMember m1 = new PremiumMember(memberId,fullName,false);
				int writeMember = 0;
				for(int i = 0; i < members.length; i++)
				{
					if( members[i] == null)
					{
						writeMember = i;
						break;
					}
				}
				members[writeMember] = m1;
				noPM++;
			}
			else
			{
				System.out.println("Invalid Member Type");
			}
			System.out.println("Create a new Member? (y)yes/(n)no: ");
			newmember = newMember.nextLine();
		}
	}
	private void removeMember(Holding[] holdings, Member[] members)					//remove Member
	{
		Scanner memberSearch = new Scanner(System.in);
		System.out.println("Remove Member: ");
		String memSearch = memberSearch.nextLine();
	
		int removeMember = 0;
		char memberType = '-';
		boolean match = false;											//check for matching user. set to false
		if(memSearch.equalsIgnoreCase("exit") != true && memSearch.equalsIgnoreCase("e") != true)
		{
			try
			{
				for(int i = 0; i < members.length; i++)
				{
					if((memSearch.charAt(0) == 's' && memSearch.charAt(1) == '0') || (memSearch.charAt(0) == 'p' && memSearch.charAt(1) == '0'))
					{
						if(members[i] != null && memSearch.equalsIgnoreCase(members[i].getID()) == true)
						{
							removeMember = i;
							memberType = members[i].getID().charAt(0);			//gets user type
							match = true;										//sets match to true
							break;
						}
					}
					else if(members[i] != null && memSearch.equalsIgnoreCase(members[i].getFullName()) == true)  //checks user names ignoring null
					{
						removeMember = i;
						memberType = members[i].getID().charAt(0);			//gets user type
						match = true;												//sets match to true
						break;
					}	
				}
				if(match == true)											//if it's a match delete user
				{
					char delete;
					members[removeMember].print(holdings);
					System.out.print("Are you sure you want to delete "+memSearch+" (y)yes/(n)no: ");
					Scanner keyboard = new Scanner(System.in);
					delete = keyboard.next().charAt(0);					//Confirmation to delete
					if(delete == 'n' || delete == 'N')
					{
						System.out.println("Member: "+memSearch +" not deleted");
					}
					else if(delete == 'y' || delete == 'Y')
					{		
						if(memberType == 's')						//if standard member reduce total number of standard members by 1
							{
								noSM--;
							}
						else if( memberType == 'p')					//if premium member reduce total by 1
						{
							noPM--;
						}
						members[removeMember] = null;
						System.out.println("MEMBER DELETED");
					}
				}
				else													//if match is not found print
				{
					throw new NoMemberFoundException();
					
				}
			}catch(NoMemberFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	private void borrowHolding(Holding[] holdings, Member[] members,DateTime todaysDate)		//borrow Holding
	{
		System.out.print("Enter member name: ");
		Scanner MS = new Scanner(System.in);
		String memberSearch = MS.nextLine();
		int mem = 0;
		boolean memMatch = false;
		try
		{
			for(int i = 0; i < members.length; i++)							//go through member array until a matching name is found
			{
				try
				{
					if(members[i] != null && memberSearch.equalsIgnoreCase(members[i].getFullName()) == true && members[i].getInactive() == false)
					{
						System.out.println("Member "+ memberSearch + " details are:");
						members[i].print(holdings);
						mem = i;
						memMatch = true;
						break;
					}
					else if(members[i] != null && memberSearch.equalsIgnoreCase(members[i].getFullName()) == true && members[i].getInactive() == true)
					{
						throw new MemberInactiveException();			//throw exception if member is inactive
					}
				}catch(MemberInactiveException e)
				{
					System.out.println(e.getMessage());					//catch and print error message
				}
			}
			if(memMatch == true)
			{
				System.out.print("Enter Title of holding: ");
				Scanner HS = new Scanner(System.in);
				String holdingSearch = HS.nextLine();
				
				do																//use do while loop to allow users to borrow books without exiting
				{
					boolean match = false;
					int holdingNo = 0;
					try
					{
						for(int i = 0; i < holdings.length; i++)					//go through array until title matches a holding
						{
							try
							{
								if(holdings[i] != null && holdingSearch.equalsIgnoreCase(holdings[i].getTitle()) == true && holdings[i].getStatus() == false)
								{	
									if(holdings[i].isOnLoan() == false) 
									{
										System.out.println("Holding " + holdingSearch +" is: ");
										holdings[i].print();
										holdingNo = i;
										match = true;
										break;
									}
									else
									{
										throw new OnLoanException();		//throw exception if holding is on loan
									}
								}
								else if(holdings[i] != null && holdingSearch.equalsIgnoreCase(holdings[i].getTitle()) == true && holdings[i].getStatus() == true)
								{
									throw new ItemInactiveException();			//throw exception if item has be deactivated
								}
							}catch(OnLoanException e)
							{
								System.out.println(e.getMessage());				//catch on loan exception and print message
							}
							catch(ItemInactiveException e)
							{
								System.out.println(e.getMessage());				//catch inactive exception and print message
							}
						}
						if(match == true)
						{
							System.out.print("Are you sure you want to borrow this item? (y)yes/(n)no ");
							Scanner keyboard = new Scanner(System.in);
							String input = keyboard.nextLine();
							char conformation = input.charAt(0);
							try
							{
								if(conformation == 'y' && members[mem].getCredit() >= holdings[holdingNo].getDefaultLoanFee())
								{
									holdings[holdingNo].setBorrowDate();							//set time item is borrowed
									members[mem].assignHolding(holdings[holdingNo]);				//assign holding from holding array to matching member's borrowed array
									holdings[holdingNo].borrowHolding();									//set isOnLoan of current holding to true
									int credit = members[mem].getCredit() - holdings[holdingNo].getDefaultLoanFee();		//calculate credit after loan fee has been subtracted from member
									members[mem].CurrentCredit(credit);								//change members current credit
									System.out.println("Member's credit remaining: $"+credit);		//print members remaining credit
								}
								else if(conformation == 'y' && members[mem].getCredit() < holdings[holdingNo].getDefaultLoanFee())
								{
									throw new InsufficientCreditException();				//throw exception if member does not have enough funds
								}
							}catch(InsufficientCreditException e)
							{
								System.out.println(e.getMessage());					//catch exception
							}
						}
						else
						{
							throw new NoHoldingFoundException();				//throw exception if not holding is found
						}
					}catch(NoHoldingFoundException e)
					{
						System.out.println(e.getMessage());						//print error message if no holding is found
					}
					System.out.print("Enter Title of holding: ");					
					holdingSearch = HS.nextLine();
				}while(holdingSearch.equals("exit") == false && holdingSearch.equals("e") == false);
			}
			else
			{
				throw new NoMemberFoundException();		//throw exception if no member is found
			}
		}catch(NoMemberFoundException e)
		{
			System.out.println(e.getMessage());				//print error message if no member has been found
		}
	}
	private void returnHolding(Holding[] holdings, Member[] members,DateTime returnDate)				//return Holding
	{
		System.out.print("Enter member name: ");								//enter member
		Scanner MS = new Scanner(System.in);
		String memberSearch = MS.nextLine();
		int mem = 0;
		boolean memMatch = false;
		try
		{
			for(int i = 0; i < members.length; i++)							//go through member array until a matching name is found
			{
				if(members[i] != null && memberSearch.equalsIgnoreCase(members[i].getFullName()) == true)
				{
					System.out.println("Member "+ memberSearch + " details are:");
					members[i].print(holdings);
					mem = i;
					memMatch = true;
					break;
				}
			}
			if(memMatch == true)
			{
				System.out.print("Enter Title of holding: ");
				Scanner HS = new Scanner(System.in);
				String holdingTitle = HS.nextLine();
				
											
				while(holdingTitle.equals("exit") != true && holdingTitle.equals("e") != true)
				{
					if(members[mem].hasHolding(holdingTitle) == true)					//check if member has holding
					{
						boolean match = false;
						int holdingNo = 0;
						try
						{
							for(int i = 0; i < holdings.length; i++)					//go through array until title matches a holding
							{
								if(holdings[i] != null && holdingTitle.equalsIgnoreCase(holdings[i].getTitle()) == true )
								{	
									holdingNo = i;
									match = true;
									break;
								}
							}
							if(match = true)
							{
								//if the difference between date returned and date borrowed is less that loan period return the holding.
								if(holdings[holdingNo].overDue(returnDate) == false)
								{				
									members[mem].getCurrentHoldings(holdingTitle);				//find holding in member array of holdings and return
									int credit = members[mem].getCredit() + holdings[holdingNo].getDefaultLoanFee();		//add loan fee back to member
									members[mem].CurrentCredit(credit);	
									System.out.println("Remaining credit: $"+members[mem].getCredit());
									System.out.println("Holding " + holdingTitle +" is available");
									holdings[holdingNo].returnHolding();												//set to available
									break;
								}
								else	//calculate late fee if holding is overdue
								{
									System.out.println("Holding is overdue by " +holdings[holdingNo].getOverDueDays() +" days");
									System.out.println("Late fee is: $"+holdings[holdingNo].calculateLateFee());
									int credit = members[mem].getCredit() + holdings[holdingNo].getDefaultLoanFee();		//add loan fee back to member
									members[mem].CurrentCredit(credit);	
									try
									{
										if(holdings[holdingNo].calculateLateFee() <= members[mem].getCredit())			//if late fee is less than members credit return the book
										{
											members[mem].setCredit(holdings[holdingNo].calculateLateFee());
											System.out.println("Remaining credit: $"+members[mem].getCredit());
											members[mem].getCurrentHoldings(holdingTitle);				//find holding in member array of holdings and return
											holdings[holdingNo].returnHolding();									//set to available
											break;
										}
										else
										{
											throw new InsufficientCreditException();				//throw error if member does not have enough money
										}
									}catch(InsufficientCreditException e)							//if member does not have enough money charge member to return to max and return holding
									{
										System.out.println(e.getMessage());
										int pay = members[mem].getMaxCredit() - members[mem].getCredit();
										members[mem].resetCredit();
										System.out.println("Balance was reset to max. Member owes: $" +pay);				//notify member how much they owe 
										members[mem].setCredit(holdings[holdingNo].calculateLateFee());
										System.out.println("Remaining credit: $"+members[mem].getCredit());
										members[mem].getCurrentHoldings(holdingTitle);				//find holding in member array of holdings and return;
										holdings[holdingNo].returnHolding();											//set to available
									}
								}
							}
							if(match == false)
							{
								throw new NoHoldingFoundException();		//throw exception if holding is not found
							}
						}catch(NoHoldingFoundException e)
						{
							System.out.println(e.getMessage());		//print error message and then member details with current holdings
							members[mem].print(holdings);
						}
						System.out.println("Enter Title of holding: ");
						holdingTitle = HS.nextLine();
					}
				}
			}
			else
			{
				throw new NoMemberFoundException();							//throw no member found
			}
		}catch(NoMemberFoundException e)
		{
			System.out.println(e.getMessage());								//print error message
		}
	}
 	private void PrintHoldings(Holding[] holdings)  								//print all holdings
	{
 		noH = noBH + noVH;
		System.out.println(noH);
		if(noH > 0)
		{
				for(int i = 0; i < holdings.length; i++)
				{
					if(holdings[i] != null)
					{
						holdings[i].print();
					}
				}
			}
		else
		{
			System.out.println("There are no Holdings");
		}
	}
	private void PrintMembers(Member[] members, Holding[] holdings)				//print all members
	{
		noM = noSM + noPM;
		System.out.println(noM);
		if(noM > 0)
		{
			for(int i = 0; i < members.length; i++)
				{
					if(members[i] != null)
					{
						members[i].print(holdings);
					}
				}
			}
		else
		{
			System.out.println("There are no Members");
		}
	}
	private void searchHoldings(Holding[] holdings, Member[] members)			// search for a holding
	{
		System.out.print("Search by holding title or ID: ");
		Scanner HS = new Scanner(System.in);
		String holdingSearch = HS.nextLine();
		while(holdingSearch.equals("exit") != true && holdingSearch.equals("e") != true)
		{
			boolean match = false;
			int holdingNo = 0;
			try
			{
				for(int i = 0; i < holdings.length; i++)					//go through array until title matches a holding
				{
					//search by holding ID
					if((holdingSearch.charAt(0) == 'b' && holdingSearch.charAt(1) == '0') || (holdingSearch.charAt(0) == 'v' && holdingSearch.charAt(1) == '0'))
					{
							if(holdings[i] != null && holdingSearch.equalsIgnoreCase(holdings[i].getID()) == true)	//if the ID is equal to the one user is searching for save array position
							{
								System.out.println("Holding " + holdingSearch +" is: ");
								holdings[i].print();
								holdingNo = i;
								match = true;
								break;
							}
					}
					//search by holding title
					else if(holdings[i] != null && holdingSearch.equalsIgnoreCase(holdings[i].getTitle()) == true)
					{	
						System.out.println("Holding " + holdingSearch +" is: ");
						holdings[i].print();
						holdingNo = i;
						match = true;
						break;
					}
				}
				if(match == true)
				{
					if(admin == true && holdings[holdingNo].getStatus() == false)
					{
						Scanner admin_input = new Scanner(System.in);
						System.out.print("Do you wish to deactivate "+holdingSearch +" (y)yes/(n)no: ");
						String adminInput = admin_input.nextLine();
						if(adminInput.equalsIgnoreCase("yes") || adminInput.equalsIgnoreCase("y") == true) // allow admin to deactivate holding
						{
							holdings[holdingNo].deactivate();
							System.out.println("Holding was deactivated");
						}
						else if(adminInput.equalsIgnoreCase("no") == true || adminInput.equalsIgnoreCase("n") == true) 
						{
							System.out.println("Holding was not deactivated");
						}
					}
					else if(admin == true && holdings[holdingNo].getStatus() == true)			//allow admin to activate a deactivated holding	
					{
						Scanner admin_input = new Scanner(System.in);
						System.out.print("Do you wish to activate "+holdingSearch +" (y)yes/(n)no: ");
						String adminInput = admin_input.nextLine();
						if(adminInput.equalsIgnoreCase("yes") || adminInput.equalsIgnoreCase("y") == true)
						{
							holdings[holdingNo].activate();
							System.out.println("Holding was activated");
						}
						else if(adminInput.equalsIgnoreCase("no") == true || adminInput.equalsIgnoreCase("n") == true)
						{
							System.out.println("Holding was not activated");
						}
					}
					else				//if holding is not on loan allow user to borrow it
					{
						if(holdings[holdingNo].isOnLoan() == false)
						{
							System.out.println("Do you wish to borrow " +holdingSearch + "(y)yes/(n)no: ");
							Scanner user_input = new Scanner(System.in);
							String input = user_input.nextLine();
							char x = input.charAt(0);
							if(x == 'y')
							{
								System.out.print("Enter member name: ");
								Scanner MS = new Scanner(System.in);
								String memberSearch = MS.nextLine();
								int mem = 0;
								for(int i = 0; i < members.length; i++)							//go through member array until a matching name is found
								{
									if(members[i] != null && memberSearch.equalsIgnoreCase(members[i].getFullName()) == true && members[i].getInactive() == false)
									{
										System.out.println("Member "+ memberSearch + " details are:");
										members[i].print(holdings);
										mem = i;
										break;
									}
								}
								members[mem].assignHolding(holdings[holdingNo]);				//assign holding from holding array to matching member's borrowed array
								holdings[holdingNo].borrowHolding();
							}
						}
					}
				}
				else if(match == false)
				{
					throw new NoHoldingFoundException();
				}
			}catch(NoHoldingFoundException e)
			{
			System.out.println(e.getMessage());
			}
			System.out.println("Search by Title of holding: ");
			holdingSearch = HS.nextLine();
		}
	}
	private void searchMembers(Member[] members, Holding[] holdings)				//search for a member
	{
		System.out.print("Enter member name or ID: ");
		Scanner MS = new Scanner(System.in);
		String memberSearch = MS.nextLine();
		while(memberSearch.equals("exit") != true && memberSearch.equals("e") != true)
		{
			try
			{
				int memberNo = 0;
				boolean memMatch = false;
				for(int i = 0; i < members.length; i++)							//go through member array until a matching name is found
				{
					//search by member ID
					if((memberSearch.charAt(0) == 's' && memberSearch.charAt(1) == '0')||(memberSearch.charAt(0) == 'p' && memberSearch.charAt(1) == '0'))
					{
						if(members[i] != null && memberSearch.equalsIgnoreCase(members[i].getID()) == true)
						{
							System.out.println("Member "+ memberSearch + " details are:");
							members[i].print(holdings);
							memberNo = i;
							memMatch = true;
						}							
					}
					else				//search by member name
					{
						if(members[i] != null && memberSearch.equalsIgnoreCase(members[i].getFullName()) == true)
						{
							System.out.println("Member "+ memberSearch + " details are:");
							members[i].print(holdings);
							memberNo = i;
							memMatch = true;
						}
					}
				}
				if(memMatch == true)
				{				
					if(admin == true && members[memberNo].getInactive() == false)			//allow admin to deactivate a member
					{
						Scanner admin_input = new Scanner(System.in);
						System.out.print("Do you wish to deactivate "+memberSearch +" (y)yes/(n)no: ");
						String adminInput = admin_input.nextLine();
						if(adminInput.equalsIgnoreCase("yes") || adminInput.equalsIgnoreCase("y") == true)
						{
							members[memberNo].deactivate();
							System.out.println("Member was deactivated");
						}
						else if(adminInput.equalsIgnoreCase("no") == true || adminInput.equalsIgnoreCase("n") == true)
						{
							System.out.println("Member was not deactivated");
						}
						if(members[memberNo].getCredit() != members[memberNo].getMaxCredit()) //allow admin to reset member credit
						{
							System.out.print("Give member "+members[memberNo].getFullName()+" max credit (y)yes/(n)no: ");
							adminInput = admin_input.nextLine();
							if(adminInput.equalsIgnoreCase("yes") || adminInput.equalsIgnoreCase("y") == true)
							{
								int credit = members[memberNo].getMaxCredit() - members[memberNo].getCredit();
								members[memberNo].resetCredit();
								System.out.println("Member's credit was reset to $"+members[memberNo].getCredit());
								System.out.println("Member owes $"+credit);
							}
							else if(adminInput.equalsIgnoreCase("no") == true || adminInput.equalsIgnoreCase("n") == true)
							{
								System.out.println("Member credit was not reset");
							}
						}	
					}
					else if(admin == true && members[memberNo].getInactive() == true)			//allow admin to activate a deactivated member
					{
						Scanner admin_input = new Scanner(System.in);
						System.out.print("Do you wish to activate "+memberSearch +" (y)yes/(n)no: ");
						String adminInput = admin_input.nextLine();
						if(adminInput.equalsIgnoreCase("yes") || adminInput.equalsIgnoreCase("y") == true)
						{
							members[memberNo].activate();
							System.out.println("Member was activated");
						}
						else if(adminInput.equalsIgnoreCase("no") == true || adminInput.equalsIgnoreCase("n") == true)
						{
							System.out.println("Member was not activated");
						}
						if(members[memberNo].getCredit() != members[memberNo].getMaxCredit())					//allow admin to reset members credit
						{
							System.out.print("Give member "+members[memberNo].getFullName()+" max credit (y)yes/(n)no: ");
							adminInput = admin_input.nextLine();
							if(adminInput.equalsIgnoreCase("yes") || adminInput.equalsIgnoreCase("y") == true)
							{
								int credit = members[memberNo].getMaxCredit() - members[memberNo].getCredit();
								members[memberNo].resetCredit();
								System.out.println("Member's credit was reset to $"+members[memberNo].getCredit());
								System.out.println("Member owes $"+credit);
							}
							else if(adminInput.equalsIgnoreCase("no") == true || adminInput.equalsIgnoreCase("n") == true)
							{
								System.out.println("Member credit was not reset");
							}
						}
					}
				}
				else
				{
					throw new NoMemberFoundException();
				}
			}catch(NoMemberFoundException e)
			{
				System.out.println(e.getMessage());
			}
			System.out.print("Enter member name or ID: ");
			memberSearch = MS.nextLine();
		}
	}
	protected void saveFile(Holding[] holdings, Member[] members) throws IOException	//save to a file
	{
		String fileName = "Library.txt";
		FileWriter fw = new FileWriter(fileName);
			for(int i = 0; i < holdings.length; i++)
			{
					try {
						if(holdings[i] != null)
						{
							fw.write(holdings[i].toString());			//write holdings to file
							fw.write("\n");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			for(int i = 0; i < members.length; i++)
			{
				try
				{
					if(members[i] != null)
					{
						fw.write(members[i].toString());			//write members to file
						for(int x = 0; x < members[i].currentHoldings.length; x ++)
						{
							try
							{
								if(members[i].currentHoldings[x] != null)
								{
									fw.write(":");
									fw.write(members[i].currentHoldings[x].toString());			//write member holding array to file
								}
									
							}catch (IOException e)
							{
								e.printStackTrace();
							}
						} 
						fw.write(":_");
						fw.write("\n");
					}
				}catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			fw.close();
			System.out.println("File Saved");
	}
	protected void loadFile(Holding[] holdings, Member[] members)					//Load a File
	{
		String fileName = "Library.txt";
		try
		{
			Scanner inputLibrary = new Scanner(new File(fileName));
			while(inputLibrary.hasNextLine())
			{
				String line = inputLibrary.nextLine();
				String[] temp = line.split(":");			//create an array of strings from line
				String ID = temp[0];
				if(temp[0].charAt(0) == 'b')
				{
					String title = temp[1];
	//				int loanFee =Integer.parseInt(temp[2]);
	//				int maxLoanPeriod = Integer.parseInt(temp[3]);
					String onLoan = temp[4];
					String status = temp[5];
					String borrowDate  = temp[6];
					if(borrowDate.equals("-") == false)
					{
						String [] dateTemp = temp[6].split("-");				//create an array from date string
						int day =Integer.parseInt(dateTemp[2]);
						int month =Integer.parseInt(dateTemp[1]);
						int year =Integer.parseInt(dateTemp[0]);
						Holding h1 = new Book(ID,title,onLoan,status,day,month,year);			//create a new book object with borrow date
						int writeHolding = 0;
						for(int x = 0; x < holdings.length; x++)				//print to the first empty space in the array
						{
							if( holdings[x] == null)
							{
								writeHolding = x;
								break;
							}
						}
						holdings[writeHolding] = h1;
						noBH++;
					}
					else 
					{
						Holding h1 = new Book(ID,title,onLoan,status);			//create a new book object
						int writeHolding = 0;
						for(int x = 0; x < holdings.length; x++)				//print to the first empty space in the array
						{
							if( holdings[x] == null)
							{
								writeHolding = x;
								break;
							}
						}
						holdings[writeHolding] = h1;
						noBH++;
					}
					System.out.println("File: "+ ID +" Loaded");
				}
				else if(temp[0].charAt(0) == 'v')	
				{
					String title = temp[1];
					int loanFee =Integer.parseInt(temp[2]);
	//				int maxLoanPeriod = Integer.parseInt(temp[3]);
					String onLoan = temp[4];
					String status = temp[5];
					String borrowDate = temp[6];
					if(borrowDate.equals("-") == false)
					{
						String [] dateTemp = temp[6].split("-");
						int day =Integer.parseInt(dateTemp[2]);
						int month =Integer.parseInt(dateTemp[1]);
						int year =Integer.parseInt(dateTemp[0]);
						Holding h1 = new Video(ID,title,loanFee, onLoan, status,day,month,year);				//create a new video object with borrow date
						int writeHolding = 0;
						for(int x = 0; x < holdings.length; x++)				//print to the first empty space in the array
						{
							if( holdings[x] == null)
							{
								writeHolding = x;
								break;
							}
						}
						holdings[writeHolding] = h1;
						noVH++;
					}
					else
					{
						Holding h1 = new Video(ID,title,loanFee, onLoan, status);			//create a new video object
						int writeHolding = 0;
						for(int x = 0; x < holdings.length; x++)				//print to the first empty space in the array
						{
							if( holdings[x] == null)
							{
								writeHolding = x;
								break;
							}
						}
						holdings[writeHolding] = h1;
						noVH++;
					}
					System.out.println("File: "+ ID +" Loaded");
				}
				else if(temp[0].charAt(0) == 's')
				{
					String fullName = temp[1];
					int currentCredit = Integer.parseInt(temp[2]);
					String memStatus = temp[3];
					Member m1 = new StandardMember(ID,fullName,currentCredit,memStatus);		//create a new standard member
					int writeMember = 0;
					for(int x = 0; x < members.length; x++)				//print to the first empty space in the array
					{
						if( members[x] == null)
						{
							writeMember = x;
							break;
						}
					}
					members[writeMember] = m1;
					noSM++;
					int b = 4;
					String borrowedHoldings = temp[b];
					while(temp[b].equals("_") == false)						//while loop to create holding objects for member and store them in members holding array
					{
						borrowedHoldings = temp[b];
						if(temp[b].charAt(0) == 'b')
						{
							String title = temp[b+1];
							String onLoan = temp[b+4];
							String status = temp[b+5];
							String borrowDate  = temp[b+6];
							if(borrowDate.equals("-") == false)
							{
								String [] dateTemp = temp[b+6].split("-");
								int day =Integer.parseInt(dateTemp[2]);
								int month =Integer.parseInt(dateTemp[1]);
								int year =Integer.parseInt(dateTemp[0]);
								Holding h1 = new Book(borrowedHoldings,title,onLoan,status,day,month,year);
								int writeHolding = 0;
								for(int x = 0; x < members[writeMember].currentHoldings.length; x++)				//print to the first empty space in the array
								{
									if(members[writeMember].currentHoldings[x] == null)
									{
										writeHolding = x;
										break;
									}
								}
								members[writeMember].currentHoldings[writeHolding] = h1;
								noBH++;
							}
							else 
							{
								Holding h1 = new Book(borrowedHoldings,title,onLoan,status);
								int writeHolding = 0;
								for(int x = 0; x < members[writeMember].currentHoldings.length; x++)				//print to the first empty space in the array
								{
									if( members[writeMember].currentHoldings[x] == null)
									{
										writeHolding = x;
										break;
									}
								}
								members[writeMember].currentHoldings[writeHolding] = h1;
								noBH++;
							}
							b+=7;
						}
						else if(temp[b].charAt(0) == 'v')
						{
							String title = temp[b+1];
							int loanFee =Integer.parseInt(temp[b+3]);
							String onLoan = temp[b+4];
							String status = temp[b+5];
							String borrowDate = temp[b+6];
							if(borrowDate.equals("-") == false)
							{
								String [] dateTemp = temp[b+6].split("-");
								int day =Integer.parseInt(dateTemp[2]);
								int month =Integer.parseInt(dateTemp[1]);
								int year =Integer.parseInt(dateTemp[0]);
								Holding h1 = new Video(borrowedHoldings,title,loanFee, onLoan, status,day,month,year);
								int writeHolding = 0;
								for(int x = 0; x < members[writeMember].currentHoldings.length; x++)				//print to the first empty space in the array
								{
									if(members[writeMember].currentHoldings[x] == null)
									{
										writeHolding = x;
										break;
									}
								}
								members[writeMember].currentHoldings[writeHolding] = h1;
								noVH++;
							}
							else
							{
								Holding h1 = new Video(borrowedHoldings,title,loanFee, onLoan, status);
								int writeHolding = 0;
								for(int x = 0; x < members[writeMember].currentHoldings.length; x++)				//print to the first empty space in the array
								{
									if(members[writeMember].currentHoldings[x] == null)
									{
										writeHolding = x;
										break;
									}
								}
								members[writeMember].currentHoldings[writeHolding] = h1;
								noVH++;
							}
							b+=7;
						}
					}
					System.out.println("File: "+ ID +" Loaded");
				}
				else if(temp[0].charAt(0) == 'p')				
				{
					String fullName = temp[1];
					int currentCredit = Integer.parseInt(temp[2]);
					String memStatus = temp[3];
					Member m1 = new PremiumMember(ID,fullName,currentCredit,memStatus);				//create a premium member
					int writeMember = 0;
					for(int x = 0; x < members.length; x++)				//print to the first empty space in the array
					{
						if( members[x] == null)
						{
							writeMember = x;
							break;
						}
					}
					members[writeMember] = m1;
					noPM++;
					int b = 4;
					String borrowedHoldings = temp[b];
					while(temp[b].equals("_") == false)				//while loop to create borrowed holding objects in members array
					{
						borrowedHoldings = temp[b];
						if(temp[b].charAt(0) == 'b')
						{
							String title = temp[b+1];
							String onLoan = temp[b+4];
							String status = temp[b+5];
							String borrowDate  = temp[b+6];
							if(borrowDate.equals("-") == false)
							{
								String [] dateTemp = temp[b+6].split("-");
								int day =Integer.parseInt(dateTemp[2]);
								int month =Integer.parseInt(dateTemp[1]);
								int year =Integer.parseInt(dateTemp[0]);
								Holding h1 = new Book(borrowedHoldings,title,onLoan,status,day,month,year);
								int writeHolding = 0;
								for(int x = 0; x < members[writeMember].currentHoldings.length; x++)				//print to the first empty space in the array
								{
									if(members[writeMember].currentHoldings[x] == null)
									{
										writeHolding = x;
										break;
									}
								}
								members[writeMember].currentHoldings[writeHolding] = h1;
								noBH++;
							}
							else 
							{
								Holding h1 = new Book(borrowedHoldings,title,onLoan,status);
								int writeHolding = 0;
								for(int x = 0; x < members[writeMember].currentHoldings.length; x++)				//print to the first empty space in the array
								{
									if( members[writeMember].currentHoldings[x] == null)
									{
										writeHolding = x;
										break;
									}
								}
								members[writeMember].currentHoldings[writeHolding] = h1;
								noBH++;
							}
							b+=7;
						}
						else if(temp[b].charAt(0) == 'v')
						{
							String title = temp[b+1];
							int loanFee =Integer.parseInt(temp[b+3]);
							String onLoan = temp[b+4];
							String status = temp[b+5];
							String borrowDate = temp[b+6];
							if(borrowDate.equals("-") == false)
							{
								String [] dateTemp = temp[b+6].split("-");
								int day =Integer.parseInt(dateTemp[2]);
								int month =Integer.parseInt(dateTemp[1]);
								int year =Integer.parseInt(dateTemp[0]);
								Holding h1 = new Video(borrowedHoldings,title,loanFee, onLoan, status,day,month,year);
								int writeHolding = 0;
								for(int x = 0; x < holdings.length; x++)				//print to the first empty space in the array
								{
									if( holdings[x] == null)
									{
										writeHolding = x;
										break;
									}
								}
								members[writeMember].currentHoldings[writeHolding] = h1;
								noVH++;
							}
							else
							{
								Holding h1 = new Video(borrowedHoldings,title,loanFee, onLoan, status);
								int writeHolding = 0;
								for(int x = 0; x < holdings.length; x++)				//print to the first empty space in the array
								{
									if( holdings[x] == null)
									{
										writeHolding = x;
										break;
									}
								}
								members[writeMember].currentHoldings[writeHolding] = h1;
								noVH++;
							}
							b+=7;
						}
					}
					System.out.println("File: "+ ID +" Loaded");
				}
			}
		}catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file " +fileName);
			System.exit(0);
		}
}
	protected void exitSystem(Holding[] holdings, Member[] members) throws IOException													//Exit out of system
	{
		System.out.println();
		Scanner input = new Scanner(System.in);
		System.out.println("WARNING SAVING OVERRIDES FILE");
		System.out.print("Do you to save current library state? (y)yes/(n)no: ");			//allow user to save library state
		String adminInput = input.nextLine();
		if(adminInput.equalsIgnoreCase("yes") || adminInput.equalsIgnoreCase("y") == true)
		{
			saveFile(holdings,members);
		}
		else if(adminInput.equalsIgnoreCase("no") == true || adminInput.equalsIgnoreCase("n") == true) 
		{
			System.out.println("File was not saved. ");
		}
		Seperator();
		System.out.print("\n||");
		System.out.print("\t\tExit System\t\t||\n");
		Seperator();
		System.out.println();
	}
}