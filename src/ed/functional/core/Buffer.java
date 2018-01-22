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
	 * Valid values are between 0 and lines.length - 1 inclusive. 
	 */
	private final int address;
	
	/**
	 * Create a new empty buffer.
	 * Current line is 0.
	 */
	public Buffer()
	{
		this(ListUtility.newList(), 0);
	}
	
	/**
	 * Create a new buffer with the current line equal to the last line.
	 * @param lines Identical to Buffer(List, int).
	 */
	public Buffer(List<String> lines)
	{
		this(lines, lines.size());
	}
	
	/**
	 * Create a new buffer.
	 * @param lines The lines to make up the new buffer.
	 * Null items are silently ignored. The list itself may not be null.
	 * @param address The current line.
	 * @throws NullPointerException Iff lines is null.
	 */
	public Buffer(List<String> lines, int address)
	{
		if (lines == null)
		{
			throw new NullPointerException();
		}
		
		this.lines = ListUtility.filterList(lines, Predicates::isNonNull);	
		this.address = address;
	}

	/**
	 * Get a copy of the lines that make up this buffer.
	 * @return A non null, potentially empty, list of lines.
	 */
	public List<String> getLines()
	{
		return ListUtility.copy(lines);
	}
	
	/**
	 * Return the current address:
	 * The current line to be modified.
	 * @return 0 to number of lines in a file.
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
		return new Buffer(lines, address);
	}
	

	/**
	 * The line is added to the position of the current address, shifting all elements
	 * on and after the current address to their address + 1.
	 * @param line The line to add. If null the returned buffer is equal to the callee buffer.
	 * @return A new buffer with line added and the current line remains unchanged (i.e. pointing
	 * to the newly added line).
	 * @throws IndexOutOfBoundsException Iff address is < 0 or >= lines.size().
	 */
	public Buffer addToCurrentAddress(String line)
	{
		return new Buffer(ListUtility.copyAdd(lines, line, constrainLowerAddress(address)), getAddress());		
	}
	
	/**
	 * Add an item to the end of the buffer.
	 * addToCurrentAddress(String) can add from start -> end - 1
	 * but not at the final position because the item after it is 
	 * pushed one along.
	 * @param line The line to add at the end of the buffer.
	 * @return A new buffer with the new line at the end.
	 * The current address remains the same.
	 */
	public Buffer addToEnd(String line)
	{
		return new Buffer(ListUtility.copyAdd(lines, line), getAddress());
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean isEqual = false;
		
		if (o instanceof Buffer)
		{
			Buffer other = (Buffer) o;
			isEqual = this.lines.equals(other.lines) && this.address == other.address;
		}
		
		return isEqual;
	}
	
	/**
	 * Treat getAddress() as a valid lines position where
	 * 0 or 1 is the first element, 2 is the second element
	 * and so on.
	 * @param address The address to constrain.
	 * @return 0 for input 0, address - 1 otherwise.
	 */
	private int constrainLowerAddress(int address)
	{
		if (address == 0)
		{
			return 0;
		}
		
		return address - 1;
	}
}
