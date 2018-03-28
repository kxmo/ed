package ed.functional.core.action;

import java.util.Optional;

import ed.functional.core.Action;

public class QuitWithoutChecking implements Action
{
	@Override
	public Optional<String> execute()
	{
		System.exit(0);
		return Optional.empty();
	}
}
