package ed.functional.core.command;

import ed.State;
import ed.functional.core.Command;
import ed.functional.core.action.Print;

public class Quit
{
	public static State addAction(State state, String[] args)
	{
		if (state.noChangesSinceLastWrite() || state.mostRecentAction().equals(Command.q))
		{
			return QuitWithoutChecking.addAction(state, args);
		}
		
		return state.addAction(new Print(System.out::println, "?"));
	}
}
