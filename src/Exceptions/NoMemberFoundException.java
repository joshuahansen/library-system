package Exceptions;
//exception to throw when no member is found by name or id
public class NoMemberFoundException extends Exception
{
	public NoMemberFoundException()
	{
		super("No member found with that ID or Name");
	}
}
