package ed.functional.core.command;

import java.util.Optional;

import ed.State;
import ed.functional.core.Action;

public class QuitWithoutChecking implements Action
{
	@Override
	public Optional<String> execute()
	{
		System.exit(0);
		return Optional.empty();
	}
	
	public static State addAction(State state, String[] unused)
	{
		return state.addAction(new QuitWithoutChecking());
	}
}
