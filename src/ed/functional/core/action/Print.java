package ed.functional.core.action;

import java.util.Optional;
import java.util.function.Consumer;

import ed.functional.core.Action;

public class Print implements Action
{
	private final Consumer<String> printer;
	private final String message;
	
	public Print(Consumer<String> printer, String message)
	{
		this.printer = printer;
		this.message = message;
	}
	
	@Override
	public Optional<String> execute()
	{
		printer.accept(message);
		return Optional.empty();
	}
}
