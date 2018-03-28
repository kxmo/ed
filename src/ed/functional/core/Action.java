package ed.functional.core;

import java.util.Optional;

/**
 * An action that may change the outside environment.
 * The implementation for common actions are in ed.functional.core.action.
 * Unique actions may be implemented within the class that uses them
 * (QuitWithoutChecking is one such example).
 */
public interface Action
{
	/**
	 * Make a side effecting change to the environment.
	 * This may include reading or writing a file, or printing to the screen.
	 * The return value will be dependent on the action, and may not always make sense.
	 */
	public Optional<String> execute();
}
