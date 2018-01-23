package ed.functional.core.tests.buffer;

import static ed.utility.TestUtility.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ed.functional.core.Buffer;

public class EqualsTests
{
	/**
	 * A standard set of values for some lines.
	 */
	private static final List<String> lines = Arrays.asList("a", "b", "c");

	@Test
	public void equals_linesAndAddress_isTrue()
	{
		assertEquals("Buffers with the same lines and address must be equal",
				createBuffer(lines, 0),
				createBuffer(lines, 0));
	}

	@Test
	public void equals_emptyBufferEmptyLines_isTrue()
	{
		Buffer empty = createBuffer();
		assertEquals(0, empty.getAddress());
		assertTrue("Empty buffer must contain no lines", empty.getLines().isEmpty());
	}

	@Test
	public void equals_linesDiffer_isFalse()
	{
		assertNotEquals("Second line differs: a not equal to b",
				createBuffer(lines, 0),
				createBuffer(replaceFirst(lines, "b", "a"), 0));
	}

	@Test
	public void equals_addressesDiffer_isFalse()
	{
		assertNotEquals("Buffers with different addresses are not equal",
				createBuffer(lines, 1),
				createBuffer(lines, 0));
	}

	@Test
	public void equals_noLinesAndLines_isFalse()
	{
		assertNotEquals("Empty buffer is not equal to a buffer with lines",
				createBuffer(),
				createBuffer(lines, 0));
	}

	@Test
	public void equals_emptyBufferLines_isFalse()
	{
		assertNotEquals("A buffer is not equal to the lines that makes it up",
				createBuffer(),
				lines);
	}

	@Test
	public void equals_fullBufferLines_isFalse()
	{
		assertNotEquals("A buffer is not equal to the lines that makes it up",
				lines,
				createBuffer(lines, 0));
	}
	
	@Test
	public void equals_emptyBufferNull_isFalse()
	{
		assertNotEquals("A buffer is not equal to null",
				null,
				createBuffer());
	}

	@Test
	public void equals_fullBufferNull_isFalse()
	{
		assertNotEquals("A buffer is not equal to null",
				null,
				createBuffer(lines, 0));
	}
}
