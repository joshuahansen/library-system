package Exceptions;
//exception to throw when holding is on loan
public class OnLoanException extends Exception
{
	
	public OnLoanException()
	{
		super("The holding is on loan");
	}
}
