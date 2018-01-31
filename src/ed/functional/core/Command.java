package ed.functional.core;

import java.util.function.BiFunction;

import ed.State;
import ed.functional.core.action.Print;

/**
 * The commands and implementations defined by the Ed specification.
 */
public enum Command
{
	LineNumber ("($)=", Command::lineNumber);

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
	
	private static State lineNumber(State state, String[] unused)
	{
		int address = state.buffers().peek().getAddress();
		String message = String.format("%d\n", address);
		Action nextAction = new Print(System.out::println, message);
		return state.addAction(nextAction);
	}
}
