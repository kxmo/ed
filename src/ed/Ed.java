package ed;

/**
 * 
 * This is a 1997 specification compliant implementation of Ed.
 * The specification can be found at: http://pubs.opengroup.org/onlinepubs/7908799/xcu/ed.html
 *
 */
public class Ed
{
	private State currentState;
	
	public Ed(State currentState)
	{
		this.currentState = currentState;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Implementation is a work in progress");
	}
}
