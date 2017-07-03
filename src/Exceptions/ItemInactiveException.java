package Exceptions;
//exception to throw when an item is inactive and tries to be used 
public class ItemInactiveException extends Exception
{
	
	public ItemInactiveException()
	{
		super("The item is not active");
	}
}
