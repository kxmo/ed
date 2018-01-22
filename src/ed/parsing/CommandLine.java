package ed.parsing;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CommandLine
{
	public static boolean suppress(String[] args)
	{
		return getIndexOf("-s", args).isPresent();
	}
	
	public static String getPromptString(String[] args)
	{
		String prompt;
		Optional<Integer> position = getIndexOf("-p", args);
		
		try
		{
			prompt = args[position.get() + 1];
		}
		catch(NoSuchElementException | IndexOutOfBoundsException e)
		{
			prompt = "";
		}
		
		return prompt;
	}
	
	private static <T> Optional<Integer> getIndexOf(T item, T[] items)
	{
		int potentialPosition = Arrays.asList(items).indexOf(item);
		Optional<Integer> index = Optional.of(potentialPosition);
		
		if (potentialPosition == -1)
		{
			index = Optional.empty();
		}
		
		return index;
	}
}
