package ed.functional.core;

import java.util.function.BiFunction;

import ed.State;
import ed.functional.core.action.Print;
import ed.functional.core.action.QuitWithoutChecking;

/**
 * The commands and implementations defined by the Ed specification.
 */
public enum Command
{
	LineNumber ("($)=", Command::lineNumber),
	Quit("q", Command::quit),
	QuitWithoutChecking("Q", Command::quitWithoutChecking);

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
	
	private static State quit(State state, String[] args)
	{
		if (state.noChangesSinceLastWrite() || state.mostRecentAction().equals(Quit))
		{
			return quitWithoutChecking(state, args);
		}
		
		return state.addAction(new Print(System.out::println, "?"));
	}
	
	private static State quitWithoutChecking(State state, String[] unused)
	{
		return state.addAction(new QuitWithoutChecking());
	}
}
