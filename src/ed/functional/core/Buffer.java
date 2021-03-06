package ed.functional.core;

import java.util.List;

import ed.utility.ListUtility;
import ed.utility.Predicates;

/**
 * A buffer is an ordered collection of lines with a current line set.
 * It is not possible to modify a buffer, only to create new ones with
 * modified properties.
 * 
 * The first line is addressed 1, the second 2 and so on.
 * Address 0 is always valid and returns the first line except where 
 * otherwise specified.
 */
public class Buffer
{
	/**
	 * We can avoid copying the items in `lines' for every list
	 * because String is an immutable and final class. If that 
	 * property doesn't hold for any changes to the contents of 
	 * `lines' then the contents will need to be copied every time.
	 */
	
	
	/** 
	 * The collection of text that makes up a (potentially modified) file.
	 * 
	 * null lines are not added and silently ignored.
	 */
	private final List<String> lines;
	
	/**
	 * The current line.
	 * Valid values for use are between 0 and lines.length - 1 inclusive.
	 * 
	 * The specification does not discuss values outside of 0 and the size of the file.
	 * We will treat those values as:
	 * negative: 0
	 * > size: size 
	 */
	private final int address;
	private static final int defaultAddress = 0;
	
	/**
	 * The command prompt, used for entering commands.
	 * This typically does not change between buffers but some commands
	 * (-P) do change the prompt.
	 */
	private final String prompt;
	private static final String defaultPrompt = "";
	
	/**
	 * Create a new empty buffer.
	 * Current line is 0.
	 */
	public Buffer()
	{
		this(ListUtility.newList(), defaultPrompt , defaultAddress);
	}
	
	/**
	 * Create a new buffer with the current line equal to the last line.
	 * @param lines Identical to Buffer(List, int).
	 */
	public Buffer(List<String> lines)
	{
		this(lines, defaultPrompt, lines.size());
	}
	
	/**
	 * Create a new buffer.
	 * @param lines The lines to make up the new buffer.
	 * Null items are silently ignored.
	 * If the list itself is null an empty list will be used.
	 * @param address The current line.
	 */
	public Buffer(List<String> lines, String prompt, int address)
	{
		if (lines == null)
		{
			this.lines = ListUtility.newList();
		}
		else
		{
			this.lines = ListUtility.filterList(lines, Predicates::isNonNull);
		}
		
		this.address = constrainAddress(address);
		this.prompt = prompt;
	}

	/**
	 * Get a copy of the lines that make up this buffer.
	 * @return A non null, potentially empty, list of lines.
	 */
	public List<String> getLines()
	{
		return ListUtility.copy(lines);
	}
	
	public String getLineAtAddress(int address)
	{
		return getLines().get(constrainAddress(address));
	}
	
	public String getLineAtCurrentAddress()
	{
		return getLineAtAddress(address);
	}
	
	/**
	 * Return the current address:
	 * The current line to be modified.
	 * There are no limits on the value this may hold,
	 * except when an operation depending on the address is used.
	 * In that case valid values are 0 to lines in file - 1 inclusive.
	 */
	public int getAddress()
	{
		return address;
	}
	
	/**
	 * Get a buffer with the address set to `address'
	 * @param address Position of the newly selected line.
	 * Constrained according to Buffer(List, int).
	 * @return A buffer with .equal lines, and an address value
	 * of constrain(address).
	 */
	public Buffer setAddress(int address)
	{
		return new Buffer(lines, prompt, address);
	}
	
	/**
	 * Return the current prompt.
	 */
	public String getPrompt()
	{
		return prompt;
	}
	
	/**
	 * Get the default value of the prompt.
	 */
	public static String getDefaultPrompt()
	{
		return defaultPrompt;
	}
	
	/**
	 * Get a buffer with the prompt set to `prompt'
	 * @param prompt The new prompt value.
	 * @return A buffer with .equal lines, address value, and a new prompt.
	 */
	public Buffer setPrompt(String prompt)
	{
		return new Buffer(lines, prompt, address);
	}

	/**
	 * The line is added to the position of the current address, shifting all elements
	 * on and after the current address to their address + 1.
	 * @param line The line to add. If null the returned buffer is equal to the callee buffer.
	 * @return A new buffer with line added and the current line remains unchanged (i.e. pointing
	 * to the newly added line).
	 */
	public Buffer addToCurrentAddress(String line)
	{
		return new Buffer(ListUtility.copyAdd(lines, line, address), prompt, address);		
	}
	
	/**
	 * Add an item to the end of the buffer.
	 * addToCurrentAddress(String) can add from start -> end - 1
	 * but not at the final position because the item after it is 
	 * pushed one along.
	 * @param line The line to add at the end of the buffer.
	 * @return A new buffer with the new line at the end.
	 * The address is not constrained because it is not used.
	 */
	public Buffer addToEnd(String line)
	{
		return new Buffer(ListUtility.copyAdd(lines, line), prompt, address);
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean isEqual = false;
		
		if (o instanceof Buffer)
		{
			Buffer other = (Buffer) o;
			isEqual = this.lines.equals(other.lines) && this.address == other.address && this.prompt.equals(other.prompt);
		}
		
		return isEqual;
	}
	
	/**
	 * Treat getAddress() as a valid line's position where
	 * 0 or 1 is the first element, 2 is the second element
	 * and so on.
	 * Addresses below 0 are equal to 0, and line size 
	 * (inclusive) or above are line size - 1.
	 * @param address The address to constrain.
	 * @return An address within valid bounds.
	 */
	private int constrainAddress(int address)
	{
		if (address <= 0)
		{
			return 0;
		}
		
		if (address >= lines.size())
		{
			return lines.size();
		}
		
		return address;
	}
}
