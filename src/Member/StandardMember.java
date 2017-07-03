package Member;

import Holding.Holding;

public class StandardMember extends Member
{
	private int maxCredit = 35;
	
	//constructor used when creating a new member
	public StandardMember(int memberId, String fullName, boolean currentBorrowed)
	{
		super(memberId,fullName,currentBorrowed);
		this.currentCredit = resetCredit();
	}
	//constructor used when reading member from file
	public StandardMember(String memberId, String fullName, int currentCredit, String memStatus)
	{
		super(memberId,fullName,memStatus);
		this.currentCredit = currentCredit;
	}
	
	@Override
	public void print(Holding[] holdings)
	{
		super.print(holdings);
		System.out.println("Standard Members max credit is: $"+maxCredit);
		System.out.println("Current credit: $"+getCredit());
		System.out.println();
	}
	@Override
	public void setCredit(int lateFee)
	{
		currentCredit -= lateFee;
	}
	@Override
	public int CurrentCredit(int credit)
	{
		currentCredit = credit;
		return currentCredit;
	}
	@Override
	public int resetCredit()
	{
		currentCredit = maxCredit;
		return currentCredit;
	}
	@Override
	public int getMaxCredit()
	{
		return maxCredit;
	}
	@Override
	public int getCredit()
	{
		int Credit = currentCredit;
		return Credit;
	}
	@Override
	public void setMemberId(int memberId)			//create member id automatically
	{

		if(memberId < 10)
		{
			String ID = "s00000";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 100)
		{
			String ID = "s0000";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 1000)
		{
			String ID = "s000";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 10000)
		{
			String ID = "s00";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 100000)
		{
			String ID = "s0";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 1000000)
		{
			String ID = "s";
			this.memberId = ((String)ID)+memberId;
		}
	}
}