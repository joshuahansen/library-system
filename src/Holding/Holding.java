package Holding;

import Driver.DateTime;
import Driver.SystemOperations;

public abstract class Holding implements SystemOperations
{
	protected String holdingId;
	private String title;
	private boolean isOnLoan;
	private long dateBorrowed;
	private String Date;
	protected int loanFee;
	protected int maxLoanPeriod;
	protected int overDueDays;
	private boolean inactive = false;
	private int day;
	private int month;
	private int year;
	private boolean alreadyBorrowed = false;
	DateTime bDate;
	DateTime borrowDate;
	DateTime todaysDate= new DateTime();
	DateTime nextWeeksDate = new DateTime(7);
	DateTime specificDate = new DateTime(10, 6, 2016);
	
	//passing integer holdingId to auto generate a holdingId for the item
	public Holding(int holdingId, String title)
	{
		setholdingId(holdingId);
		setTitle(title);	
	}
	//new Holding constructor for using the file input
	 public Holding(String holdingId, String title, String onLoan, String status)
	{
		this.holdingId = holdingId;
		setTitle(title);
		if(onLoan.equals("no"))
		{
			returnHolding();
		}
		else if(onLoan.equals("yes"))
		{
			borrowHolding();
		}
		if(status.equals("deactive") == true)
		{
			deactivate();
		}
		else if(status.equals("avtive") == true)
		{
			activate();
		}
	}
	 //Holding constructor if the book has been borrowed
	 public Holding(String holdingId, String title, String onLoan, String status, int day, int month, int year)
		{
			this.holdingId = holdingId;
			setTitle(title);
			if(onLoan.equals("no"))
			{
				returnHolding();
			}
			else if(onLoan.equals("yes"))
			{
				borrowHolding();
			}
			if(status.equals("deactive") == true)
			{
				deactivate();
			}
			else if(status.equals("avtive") == true)
			{
				activate();
			}
			this.day = day;							//set day from file
			this.month = month;						//set month from file
			this.year = year;						//set year from file
			alreadyBorrowed = true;					//set boolean to true if this constructor has been run
			borrowDate = new DateTime(day,month,year);			//create a DateTime object for holding from file
			setBorrowDate();						//run setBorrowDate to set the borrow date to the date in file
		}

	public abstract void setholdingId(int holdingId);
	public abstract int getDefaultLoanFee();
	public abstract int getMaxLoanPeriod();
	public abstract int calculateLateFee();
	
	public void print()
	{
		System.out.println("ID:\t\t\t" +holdingId);
		System.out.println("Title:\t\t\t"+title);
		if (isOnLoan == true)
		{
			System.out.println("Holding is on loan");
			System.out.println("Holding Borrowed on "+bDate.getFormattedDate());
		}
		else if(isOnLoan == false && inactive == true)
		{
			System.out.println("Holding is inactive");
		}
		else if(isOnLoan == false && inactive == false)
		{
			System.out.println("Holding is avalible");
		}
		
	}
	public int getOverDueDays()
	{
		return overDueDays;
	}
	public String getID()
	{
		String ID = holdingId;
		return ID;
	}
	private void setTitle(String title)
	{
		if(title.length()>0)
		{
				this.title = title;
		}
		else
		{
			this.title = "Error: Enter valid Title";
		}
	}
	public String getTitle()				//return the holding title
	{
		String holdingTitle = title;
		return holdingTitle;
	}
	public void borrowHolding()
	{
		this.isOnLoan = true;
	}
	public void returnHolding()
	{
		this.isOnLoan = false;
	}
	public boolean isOnLoan()
	{
		if (isOnLoan == true)
		{
			return true;
		}
		else
			return false;
	}
	public DateTime getBorrowDate()
	{
		return bDate;
	}
	public void setBorrowDate()
	{
		if(alreadyBorrowed == true)
		{
			bDate = borrowDate;
		}
		else
		{
			bDate = todaysDate;
		}
		this.dateBorrowed = bDate.getTime();
		this.Date = bDate.toString();
	}
	public boolean overDue(DateTime returnDate)
	{
		boolean overDue = false;
		if(specificDate.diffDays(returnDate, getBorrowDate()) > getMaxLoanPeriod())
		{
			overDue = true;
			this.overDueDays = specificDate.diffDays(returnDate, getBorrowDate())- getMaxLoanPeriod();
		}
		return overDue;
		
	}
	public String getDate()
	{
		return Date; 
	}
	public String toString()
	{
		String holdingInfo;
		if(isOnLoan == true)
		{
			if(inactive == false)
			{
				holdingInfo = holdingId+":" +title+":"+loanFee+":"+maxLoanPeriod+":yes"+":active"+":"+getDate();
			}
			else
			{
				holdingInfo = holdingId+":" +title+":"+loanFee+":"+maxLoanPeriod+":yes"+":deactive"+":-";
			}
		}
		else
		{
			if(inactive == false)
			{
				holdingInfo = holdingId+":" +title+":"+loanFee+":"+maxLoanPeriod+":no"+":active"+":-";
			}
			else
			{
				holdingInfo = holdingId+":" +title+":"+loanFee+":"+maxLoanPeriod+":no"+":deactive"+":-";
			}
		}
			
		return holdingInfo;
	}
	@Override
	public boolean activate() 
	{
		this.inactive = false;
		return inactive;
		
	}
	@Override
	public boolean deactivate() 
	{
		this.inactive = true;
		return inactive;
	}
	public boolean getStatus()
	{
		boolean isInactive = false;
		if(inactive == true)
		{
			isInactive = true;
		}
		return isInactive;
	}
}