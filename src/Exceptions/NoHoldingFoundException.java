package Exceptions;
//exception to throw when no holding is found by title or id
public class NoHoldingFoundException extends Exception
{
	public NoHoldingFoundException()
	{
		super("No Holding found with that ID or Title");
	}
}
