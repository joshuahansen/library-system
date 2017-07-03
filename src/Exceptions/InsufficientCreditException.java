package Exceptions;
//Exception to throw if member does not have enough credit
public class InsufficientCreditException extends Exception
{
	public InsufficientCreditException()
	{
		super("Member does not have enough credit");
	}
}
