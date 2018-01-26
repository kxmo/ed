package ed;

import java.util.function.BiFunction;

/**
 * The commands and implementations defined by the Ed specification.
 */
public enum Command
{
	// TODO Implement Append
	Append ("a", (a, b) -> { throw new UnsupportedOperationException(); }); 

	// TODO Implement commands
	
	private final String invocation;
	private final BiFunction<State, String[], State> func;
	 
	private Command(String invocation, BiFunction<State, String [], State> func)
	{
		this.invocation = invocation;
		this.func = func;
	}
	
	public String getInvocation()
	{
		return invocation;
	}

	public State apply(State currentState, String... args)
	{
		return func.apply(currentState, args);
	}
}
