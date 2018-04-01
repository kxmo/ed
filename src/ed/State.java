package ed;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import ed.functional.core.Action;
import ed.functional.core.Buffer;
import ed.functional.core.Question;
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
	
	private final Buffer buffer;

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

	private final List<Question> questions;
	
	public State(Buffer buffer,
				Optional<String> actionResult,
				Collection<Action> actions,
				List<Action> toExecute,
				List<Question> questions)
	{
		this.buffer = buffer;
		this.actionResult = actionResult;
		this.availableActions = ListUtility.copy(actions);
		this.toExecute = toExecute;
		this.questions = questions;
	}
	
	public Buffer currentBuffer()
	{
		return buffer;
	}
	
	public Optional<Action> mostRecentAction()
	{
		if (toExecute.isEmpty())
		{
			return Optional.empty();
		}
		
		return Optional.of(toExecute.get(toExecute.size() - 1));
	}

	public State addAction(Action nextAction)
	{
		return new State(buffer,
				actionResult,
				availableActions,
				ListUtility.copyAdd(ListUtility.copyRemove(toExecute, nextAction), nextAction),
				questions);
	}
	
	public State addQuestion(String message)
	{
		return new State(buffer,
						actionResult,
						availableActions,
						toExecute,
						ListUtility.copyAdd(questions, new Question(message)));
	}

	public boolean noChangesSinceLastWrite()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public State setBuffer(Buffer current)
	{
		return new State(current,
					actionResult,
					availableActions,
					toExecute,
					questions);
	}
}
