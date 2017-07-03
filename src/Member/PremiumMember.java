package Member;

import Holding.Holding;

public class PremiumMember extends Member
{
	private int maxCredit = 45;
	
	//constructor used when create a new member
	public PremiumMember(int memberId, String fullName, boolean currentBorrowed)
	{
		super(memberId,fullName,currentBorrowed);
		this.currentCredit = resetCredit();
	}
	//constructor used when reading a member from a file
	public PremiumMember(String memberId, String fullName, int currentCredit, String memStatus)
	{
		super(memberId,fullName,memStatus);
		this.currentCredit = currentCredit;
	}
	
	@Override
	public void print(Holding[] holdings)
	{
		super.print(holdings);
		System.out.println("Premium Members max credit is: $"+maxCredit);
		System.out.println("Current credit: $"+getCredit());
		System.out.println();
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
	public void setCredit(int lateFee)
	{
		currentCredit -= lateFee;
	}
	@Override
	public int getCredit()
	{
		int Credit = currentCredit;
		return Credit;
	}
	@Override
	public int CurrentCredit(int credit)
	{
		currentCredit = credit;
		return currentCredit;
	}
	@Override
	public void setMemberId(int memberId)			//auto create a member id for new members
	{

		if(memberId < 10)
		{
			String ID = "p00000";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 100)
		{
			String ID = "p0000";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 1000)
		{
			String ID = "p000";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 10000)
		{
			String ID = "p00";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 100000)
		{
			String ID = "p0";
			this.memberId = ((String)ID)+memberId;
		}
		else if(memberId < 1000000)
		{
			String ID = "p";
			this.memberId = ((String)ID)+memberId;
		}
	}
}
