package ed.functional.core;

import java.util.function.BiFunction;

import ed.State;
import ed.functional.core.command.*;

/**
 * The commands and implementations defined by the Ed specification.
 */
public enum Command
{
	a ("a", Append::addAction),
	LN ("($)=", LineNumber::addAction),
	q ("q", Quit::addAction),
	Q ("Q", QuitWithoutChecking::addAction);

	// TODO Implement commands
	
	private final String invocation;
	private final BiFunction<State, String[], State> func;
	 
	private Command(String invocation, BiFunction<State, String[], State> func)
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
