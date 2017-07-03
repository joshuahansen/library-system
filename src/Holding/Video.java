package Holding;

public class Video extends Holding
{
	//constructor used when creating a new video
	public Video(int holdingId, String title, int loanFee)
	{
		super(holdingId, title);
		this.loanFee = loanFee;
		maxLoanPeriod = 7;
		
	}
	//constructor used when reading video from file
	public Video(String holdingId, String title, int loanFee, String onLoan, String status)
	{
		super(holdingId,title, onLoan, status);
		this.loanFee = loanFee;
		maxLoanPeriod = 7;
	}
	//constructor used when reading a borrowed video from file
	public Video(String holdingId, String title, int loanFee, String onLoan, String status,int day, int month, int year)
	{
		super(holdingId,title, onLoan, status,day,month,year);
		this.loanFee = loanFee;
		maxLoanPeriod = 7;
	}
	
	@Override
	public void print()					//print video information
	{
		super.print();
		System.out.println("Loan Fee:\t\t$"+loanFee);
		System.out.println("Max loan period:\t"+getMaxLoanPeriod() + " days");
		System.out.println();
	}
	@Override
	public int calculateLateFee()			//calculate video late fee
	{
		int lateFee = (int) (overDueDays * (0.5*loanFee));
		return lateFee;
	}
	@Override
	public void setholdingId(int holdingId)		//auto create a video holding ID
	{
		if(holdingId < 10)
		{
						String ID = "v00000";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 100)
		{
			String ID = "v0000";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 1000)
		{
			String ID = "v000";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 10000)
		{
			String ID = "v00";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 100000)
		{
			String ID = "v0";
			this.holdingId = ((String)ID)+holdingId;
		}
		else if(holdingId < 1000000)
		{
			String ID = "v";
			this.holdingId = ((String)ID)+holdingId;
		}
	}
	@Override
	public int getMaxLoanPeriod()						//return max book loan period
	{
		int loanPeriod = 7;
		return loanPeriod;
	}
	@Override
	public int getDefaultLoanFee()						//default loan fee of video
	{
		int fee = loanFee;
		return fee;
	}
}