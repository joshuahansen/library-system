package Holding;

import Driver.DateTime;

public class Book extends Holding
{
	//constructor when creating a new book
	public Book(int holdingId, String title)
	{
		super(holdingId,title);
		maxLoanPeriod = 28;
		loanFee = 10;
	
	}
	//constructor used when reading from file
	public Book(String holdingId, String title, String onLoan, String status)
	{
		super(holdingId,title,onLoan, status);
		maxLoanPeriod = 28;
		loanFee = 10;
	}
	//constructor when loading a borrowed book from file
	public Book(String holdingId, String title, String onLoan, String status, int day, int month, int year)
	{
		super(holdingId,title,onLoan, status,day,month,year);
		maxLoanPeriod = 28;
		loanFee = 10;
	}
	
	@Override			//print holding details
	public void print()
	{
		super.print();
		System.out.println("Loan Fee:\t\t"+ "$"+getDefaultLoanFee());
		System.out.println("Max loan period:\t"+getMaxLoanPeriod());
		System.out.println();
		
	}
	@Override
	public int getDefaultLoanFee()						//return loan fee for book
	{
		loanFee = 10;
		return loanFee;
	}
	@Override
	public int getMaxLoanPeriod()						//return max book loan period
	{
		return maxLoanPeriod;
	}
	@Override											//override abstract setholdingId method in Holding class
	public void setholdingId(int holdingId)				//automatically generate holdingId and return as a string
	{
		if(holdingId < 10)
		{
			String ID = "b00000";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 100)
		{
			String ID = "b0000";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 1000)
		{
			String ID = "b000";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 10000)
		{
			String ID = "b00";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 100000)
		{
			String ID = "b0";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 1000000)
		{
			String ID = "b";
			this.holdingId = ((String)ID)+holdingId;
		}
	}
	@Override
	public int calculateLateFee()						//calculate late
	{
		int lateFee = overDueDays * 2;
		return lateFee;
	}
}