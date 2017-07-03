package Exceptions;
//exception to throw when member is inactive
public class MemberInactiveException extends Exception
{
	public MemberInactiveException()
	{
		super("The member is not active\nPlease see library stuff to reavtivate");
	}
}
