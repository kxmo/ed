package ed.functional.core.tests.buffer;

import static ed.utility.TestUtility.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ed.functional.core.Buffer;

public class AddToEndTests
{
	@Test
	public void stringToEmpty_addsStringToEnd()
	{
		Buffer nonEmpty = createBuffer().addToEnd("content");

		List<String> expectedLines = Arrays.asList("content");
		Buffer expected = createBuffer(expectedLines, 0);

		assertEquals("New buffer must contain only the added line", expected, nonEmpty);
	}
	
	@Test
	public void stringsToEmptyBuffer_stringsAddedAtEnd()
	{
		Buffer nonEmpty = createBuffer().addToEnd("first").addToEnd("second").addToEnd("third");

		List<String> expectedLines = Arrays.asList("first", "second", "third");
		Buffer expected = createBuffer(expectedLines, 0);
		
		assertEquals("Items must be added to the end of the buffer", expected, nonEmpty);
	}
}
