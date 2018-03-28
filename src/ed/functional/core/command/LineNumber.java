package ed.functional.core.command;

import ed.State;
import ed.functional.core.Action;
import ed.functional.core.action.Print;

public class LineNumber
{
	public static State addAction(State state, String[] unused)
	{
		int address = state.buffers().peek().getAddress();
		String message = String.format("%d\n", address);
		Action nextAction = new Print(System.out::println, message);
		return state.addAction(nextAction);
	}
}
