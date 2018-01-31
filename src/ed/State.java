package ed;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import ed.functional.core.Action;
import ed.functional.core.Buffer;
import ed.utility.ListUtility;

public class State
{
	/*
	 * This class will contain information relating to the state of the program.
	 * This will likely include:
	 * Buffers (past and current)
	 * Commands executed
	 * Global switch commands
	 * 
	 * Potentially a mechanism for dynamically changing the state by called functions
	 * This could be implemented by having a function that can be overridden. 
	 * That function would always be called before the next event was processed.
	 */
	
	private final Stack<Buffer> buffers;

	/**
	 * The result of a side effecting action, executed by a command.
	 */
	private final Optional<String> actionResult;
	
	/**
	 * The set of actions available at this state.
	 * Certain actions change themselves - e.g. remembering previous state
	 * so we need to keep track of which actions we are dealing with at the moment.
	 * 
	 * TODO Ensure list uniqueness.
	 * We use a list because Java sets are difficult to work with.
	 */
	private final List<Action> availableActions;
	
	/**
	 * A series of actions to execute. This queue without the first item is passed to
	 * the resulting state of the first item.
	 */
	private final List<Action> toExecute;
	
	public State(Stack<Buffer> buffers, Optional<String> actionResult, Collection<Action> actions, List<Action> toExecute)
	{
		this.buffers = buffers;
		this.actionResult = actionResult;
		this.availableActions = ListUtility.copy(actions);
		this.toExecute = toExecute;
	}
	
	public Stack<Buffer> buffers()
	{
		return buffers;
	}

	public State addAction(Action nextAction)
	{
		return new State(buffers,
				actionResult,
				availableActions,
				ListUtility.copyAdd(ListUtility.copyRemove(toExecute, nextAction), nextAction));
	}
}
