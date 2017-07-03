package Member;

import Driver.SystemOperations;
import Holding.Holding;

public abstract class Member implements SystemOperations
{
	protected String memberId;
	private String fullName;
	protected int currentCredit;
	private boolean currentBorrowed;
	private boolean inactive;
	
	//constructor used when creating a new member
	public Member(int memberId, String fullName, boolean currentBorrowed)
	{
		setMemberId(memberId);
		setFullName(fullName);
		this.currentBorrowed = currentBorrowed;
	}
	//constructor used when reading member from file
	public Member(String memberId, String fullName, String memStatus)
	{
		this.memberId = memberId;
		setFullName(fullName);
		if(memStatus.equals("active") == true)
		{
			activate();
		}
		else if(memStatus.equals("deactive") == true)
		{
			deactivate();
		}
	}
	
	//Array to store the members books and videos
	public Holding[] currentHoldings = new Holding[15];
	
	//abstract classes that are overridden by StandardMember and PremiumMember 
	public abstract void setMemberId(int memberId);
	public abstract void setCredit(int lateFee);
	public abstract int resetCredit();	
	public abstract int getMaxCredit();
	public abstract int getCredit();
	public abstract int CurrentCredit(int credit);
	
	public void print(Holding[] holdings)					//print member details
	{
		System.out.println("Member ID: "+memberId);
		System.out.println("Name of Member: "+fullName);
		if(inactive == false)
		{
			System.out.println("Active Member");
		}
		else if(inactive == true)
		{
			System.out.println("Inactive Member");								//print if member has be deactivated
			System.out.println("Please see library staff to activate account");
		}
		for(int i = 0; i < currentHoldings.length; i++)
		{
			if(currentHoldings[i] != null)	
			{
				System.out.print(getAssignedHoldings(i)+", ");			//print title of holdings the member currently has
			}
		}
		System.out.println();
	}
	public void assignHolding(Holding loanObj)				//assign holdings to member array of holdings
	{
		for(int i = 0; i < currentHoldings.length; i++)							//check null
		{
			if(currentHoldings[i] == null)
			{
				currentHoldings[i] = loanObj;
				break;
			}
		}
	}
	public String getAssignedHoldings(int i)				//pass in a value and return title of holding. move for loop to another method to call
	{
		String assignedHoldings = currentHoldings[i].getTitle();
		return assignedHoldings;
	}	
 	public String getID()									//return member ID
	{
		String ID = memberId;
		return ID;
	}
	private void setFullName(String fullName)				//set members name
	{
		if(fullName.length() > 0)
		{
			this.fullName = fullName;
		}
		else
		{
			this.fullName = "Error: Enter Valid Name";
		}
	}
	public String getFullName()								//return members name
	{
		String name = fullName;
		return name;
	}
	public boolean hasHolding(String holdingTitle)			//set to true if member array has that holding
	{
		boolean hasHolding = false;
		for(int i = 0; i < currentHoldings.length; i++)
		{
			if(currentHoldings[i] != null && holdingTitle.equalsIgnoreCase(currentHoldings[i].getTitle()) == true)
			{
				hasHolding = true;
			}
		}
		return hasHolding;
	}
	public void getCurrentHoldings(String holdingTitle)		
	{
		int memberHolding = 0;
		boolean match = false;
		for(int i = 0; i < currentHoldings.length; i++)
		{
			if(currentHoldings[i] != null && holdingTitle.equalsIgnoreCase(currentHoldings[i].getTitle()) == true)
			{
				memberHolding = i;
				match = true;
				break;
			}
		}
		if(match == true)
		{
			currentHoldings[memberHolding] = null;
			System.out.println("Holding "+holdingTitle + " was returned");
		}
		else
		{
			System.out.println("Member does not have that item");
		}
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
	public boolean getInactive()
	{
		return inactive;
	}
	public String toString()
	{
		String memberInfo;
		if(inactive == false)
		{
			memberInfo = memberId+":"+fullName+":"+currentCredit+":active";
		}	
		else
		{
			memberInfo = memberId+":"+fullName+":"+currentCredit+":deactive";
		}
		return memberInfo;
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
